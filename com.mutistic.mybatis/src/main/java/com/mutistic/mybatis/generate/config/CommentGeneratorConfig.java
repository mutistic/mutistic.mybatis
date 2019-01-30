package com.mutistic.mybatis.generate.config;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * @program 自定义Java类的注释信息
 * @description
 * @author mutisitic
 * @date 2019年1月25日
 */
public class CommentGeneratorConfig extends DefaultCommentGenerator {
	/** 表示是否使用自定义注释 */
	private boolean suppressAllComments = true;

	//
	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		String shortName = compilationUnit.getType().getShortName();
		if(shortName.contains("Example") || shortName.contains("Mapper")) {
			return;
		}
		compilationUnit.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModel"));
		compilationUnit.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty"));
	}

	// 设置类的注释
	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		String table = introspectedTable.getRemarks() + "[" + introspectedTable.getFullyQualifiedTable() + "]";
//		innerClass.addJavaDocLine("/**");
//		innerClass.addJavaDocLine(" * " + table);
//		innerClass.addJavaDocLine(" * @author " + System.getProperty("user.name"));
//		innerClass.addJavaDocLine(" */");
		innerClass.addJavaDocLine("@ApiModel(\"" + table + "\")");
		innerClass.addJavaDocLine("@SuppressWarnings(\"serial\")");
	}

	// 设置实字段的注释
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		// 使用自定义注释
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
//			field.addJavaDocLine("/** " + introspectedColumn.getRemarks() + " */");
			field.addJavaDocLine("@ApiModelProperty(\"" + introspectedColumn.getRemarks() + "\")");
		}
	}

	// 设置 get方法注释
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			method.addJavaDocLine("/**");
			method.addJavaDocLine(" * getter " + introspectedColumn.getRemarks());
			method.addJavaDocLine(
					" * @return " + introspectedColumn.getActualColumnName() + " " + introspectedColumn.getRemarks());
			method.addJavaDocLine(" */");
		}
	}

	// 设置set方法注释
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			method.addJavaDocLine("/**");
			method.addJavaDocLine(" * setter " + introspectedColumn.getRemarks());
			Parameter params = method.getParameters().get(0);
			method.addJavaDocLine(" * @param " + params.getName() + " " + introspectedColumn.getRemarks());
			method.addJavaDocLine(" */");
		}
	}
	
	// 去掉mapper原始注释
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
//		super.addGeneralMethodComment(method, introspectedTable);
	}

	// 去掉mapping原始注释
	@Override
	public void addComment(XmlElement xmlElement) {
//		super.addComment(xmlElement);
	}

}
