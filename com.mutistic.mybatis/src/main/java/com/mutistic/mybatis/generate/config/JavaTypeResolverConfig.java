package com.mutistic.mybatis.generate.config;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program 自定义Java数据类型解释器
 * @description 
 * @author mutisitic
 * @date 2019年1月25日
 */
public class JavaTypeResolverConfig extends JavaTypeResolverDefaultImpl {

	public JavaTypeResolverConfig() {
		super();
		PrintUtil.one("JavaTypeResolverConfig：自定义Java数据类型解释器");
		// 由 TINYINT -> Byte 调整 Integer
		super.typeMap.put(-6, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
		PrintUtil.two("解释类型：-6=TINYINT", "由默认Byte调整为Integer");
		// 由 BIT -> Boolean 调整 Integer
		super.typeMap.put(-7, new JdbcTypeInformation("BIT", new FullyQualifiedJavaType(Integer.class.getName())));
		PrintUtil.two("解释类型：-7=BIT", "由默认Boolean调整为Integer");
	}
}
