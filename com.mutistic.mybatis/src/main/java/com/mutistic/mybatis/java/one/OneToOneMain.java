package com.mutistic.mybatis.java.one;

import com.mutistic.mybatis.java.one.dto.OneToOneDto;
import com.mutistic.mybatis.java.one.mapper.OneToOneMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program MyBatis映射关系：一对一
 * @description
 * @author mutisitic
 * @date 2019年1月21日
 */
public class OneToOneMain {
	public static void main(String[] args) {
		OneToOneMapper mapper = SqlSeesionUtil.getMapper(OneToOneMapper.class);
		PrintUtil.one("1、MyBatis映射关系：一对一");
		
		showByQueryByResultMap(mapper);
		showByQueryByAssociationResultMap(mapper);
		showByQueryByAssociationSelect(mapper);
		SqlSeesionUtil.close();
	}
	private static void showByQueryByResultMap(OneToOneMapper mapper) {
		PrintUtil.one("2、 通过ResultMap映射查询结果集：");
		OneToOneDto dto = mapper.queryByResultMap(1547720793414l);
		PrintUtil.two("2.1、查询结果：", "dto=" + dto);
	}
	private static void showByQueryByAssociationResultMap(OneToOneMapper mapper) {
		PrintUtil.one("3、 通过association的ResultMap属性映射查询结果集：");
		OneToOneDto dto = mapper.queryByAssociationResultMap(1547720793414l);
		PrintUtil.two("3.1、查询结果：", "dto=" + dto);
	}
	private static void showByQueryByAssociationSelect(OneToOneMapper mapper) {
		PrintUtil.one("4、通过association的Select根据外键查询结果集：");
		OneToOneDto dto = mapper.queryByAssociationSelect(1547720793414l);
		PrintUtil.two("4.1、查询结果：", "dto=" + dto);
	}
}
