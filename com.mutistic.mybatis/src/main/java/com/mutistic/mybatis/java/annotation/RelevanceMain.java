package com.mutistic.mybatis.java.annotation;

import com.mutistic.mybatis.java.annotation.mapper.RelevanceMapper;
import com.mutistic.mybatis.java.annotation.mode.BizAnnotation;
import com.mutistic.mybatis.java.annotation.mode.BizMore;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program MyBatis使用注解实现关联查询
 * @description 
 * @author mutisitic
 * @date 2019年1月23日
 */
public class RelevanceMain {
	public static void main(String[] args) {
		RelevanceMapper mapper = SqlSeesionUtil.getMapper(RelevanceMapper.class);
		PrintUtil.one("1、 MyBatis使用注解实现关联查询");
		showByOneToOne(mapper);
		showByOneToMore(mapper);
		SqlSeesionUtil.close();
	}
	private static void showByOneToOne(RelevanceMapper mapper) {
		PrintUtil.one("2、使用@One注解实现一对一关联查询：");

		BizMore entity = mapper.queryByOneToOne(111111l);
		PrintUtil.two("2.1、查询结果：", "BizMore=" + entity + ", BizAnnotation=" + entity.getBizAnnotation());
	}
	private static void showByOneToMore(RelevanceMapper mapper) {
		PrintUtil.one("3、使用@Many注解实现一对多关联查询：");
		BizAnnotation entity = mapper.queryByOneToMore(1548226948040l);
		PrintUtil.two("3.1、查询结果：", "BizAnnotation=" + entity + ", BizMoreList=" + entity.getBizMoreList());
	}
}
