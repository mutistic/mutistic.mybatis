package org.mybatis.generator.codegen.mybatis3.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.internal.util.messages.Messages;

public class BaseRecordGenerator extends AbstractJavaGenerator {
	private void addParameterizedConstructor(final TopLevelClass topLevelClass,
			final List<IntrospectedColumn> constructorColumns) {
		final Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setConstructor(true);
		method.setName(topLevelClass.getType().getShortName());
		this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);
		for (final IntrospectedColumn introspectedColumn : constructorColumns) {
			method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(),
					introspectedColumn.getJavaProperty()));
			topLevelClass.addImportedType(introspectedColumn.getFullyQualifiedJavaType());
		}
		final StringBuilder sb = new StringBuilder();
		final List<String> superColumns = new LinkedList<String>();
		if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
			boolean comma = false;
			sb.append("super(");
			for (final IntrospectedColumn introspectedColumn2 : this.introspectedTable.getPrimaryKeyColumns()) {
				if (comma) {
					sb.append(", ");
				} else {
					comma = true;
				}
				sb.append(introspectedColumn2.getJavaProperty());
				superColumns.add(introspectedColumn2.getActualColumnName());
			}
			sb.append(");");
			method.addBodyLine(sb.toString());
		}
		for (final IntrospectedColumn introspectedColumn3 : constructorColumns) {
			if (!superColumns.contains(introspectedColumn3.getActualColumnName())) {
				sb.setLength(0);
				sb.append("this.");
				sb.append(introspectedColumn3.getJavaProperty());
				sb.append(" = ");
				sb.append(introspectedColumn3.getJavaProperty());
				sb.append(';');
				method.addBodyLine(sb.toString());
			}
		}
		topLevelClass.addMethod(method);
	}

	private List<IntrospectedColumn> getColumnsInThisClass() {
		List<IntrospectedColumn> introspectedColumns;
		if (this.includePrimaryKeyColumns()) {
			if (this.includeBLOBColumns()) {
				introspectedColumns = (List<IntrospectedColumn>) this.introspectedTable.getAllColumns();
			} else {
				introspectedColumns = (List<IntrospectedColumn>) this.introspectedTable.getNonBLOBColumns();
			}
		} else if (this.includeBLOBColumns()) {
			introspectedColumns = (List<IntrospectedColumn>) this.introspectedTable.getNonPrimaryKeyColumns();
		} else {
			introspectedColumns = (List<IntrospectedColumn>) this.introspectedTable.getBaseColumns();
		}
		return introspectedColumns;
	}

	public List<CompilationUnit> getCompilationUnits() {
		final FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
		this.progressCallback.startTask(Messages.getString("Progress.8", table.toString()));
		final Plugin plugins = this.context.getPlugins();
		final CommentGenerator commentGenerator = this.context.getCommentGenerator();
		final FullyQualifiedJavaType type = new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());
		final TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment((CompilationUnit) topLevelClass);
		final FullyQualifiedJavaType superClass = this.getSuperClass();
		if (superClass != null) {
			topLevelClass.setSuperClass(superClass);
			topLevelClass.addImportedType(superClass);
		}
		commentGenerator.addClassComment(topLevelClass, this.introspectedTable);
		commentGenerator.addModelClassComment(topLevelClass, this.introspectedTable);
		final List<IntrospectedColumn> introspectedColumns = this.getColumnsInThisClass();
		if (this.introspectedTable.isConstructorBased()) {
			this.addParameterizedConstructor(topLevelClass, this.introspectedTable.getNonBLOBColumns());
			if (this.includeBLOBColumns()) {
				this.addParameterizedConstructor(topLevelClass, this.introspectedTable.getAllColumns());
			}
			if (!this.introspectedTable.isImmutable()) {
				this.addDefaultConstructor(topLevelClass);
			}
		}
		final String rootClass = this.getRootClass();
		for (final IntrospectedColumn introspectedColumn : introspectedColumns) {
			if (RootClassInfo.getInstance(rootClass, this.warnings).containsProperty(introspectedColumn)) {
				continue;
			}
			final Field field = JavaBeansUtil.getJavaBeansField(introspectedColumn, this.context,
					this.introspectedTable);
			if (plugins.modelFieldGenerated(field, topLevelClass, introspectedColumn, this.introspectedTable,
					Plugin.ModelClassType.BASE_RECORD)) {
				topLevelClass.addField(field);
				topLevelClass.addImportedType(field.getType());
			}
			Method method = JavaBeansUtil.getJavaBeansGetter(introspectedColumn, this.context, this.introspectedTable);
			if (plugins.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, this.introspectedTable,
					Plugin.ModelClassType.BASE_RECORD)) {
				topLevelClass.addMethod(method);
			}
			if (this.introspectedTable.isImmutable()) {
				continue;
			}
			method = JavaBeansUtil.getJavaBeansSetter(introspectedColumn, this.context, this.introspectedTable);
			if (!plugins.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, this.introspectedTable,
					Plugin.ModelClassType.BASE_RECORD)) {
				continue;
			}
			topLevelClass.addMethod(method);
		}
		final List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		if (this.context.getPlugins().modelBaseRecordClassGenerated(topLevelClass, this.introspectedTable)) {
			answer.add((CompilationUnit) topLevelClass);
		}
		return answer;
	}

	private FullyQualifiedJavaType getSuperClass() {
		FullyQualifiedJavaType superClass;
		if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
			superClass = new FullyQualifiedJavaType(this.introspectedTable.getPrimaryKeyType());
		} else {
			final String rootClass = this.getRootClass();
			if (rootClass != null) {
				superClass = new FullyQualifiedJavaType(rootClass);
			} else {
				superClass = null;
			}
		}
		return superClass;
	}

	private boolean includeBLOBColumns() {
		return !this.introspectedTable.getRules().generateRecordWithBLOBsClass()
				&& this.introspectedTable.hasBLOBColumns();
	}

	private boolean includePrimaryKeyColumns() {
		return !this.introspectedTable.getRules().generatePrimaryKeyClass()
				&& this.introspectedTable.hasPrimaryKeyColumns();
	}
}