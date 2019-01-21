package com.mutistic.mybatis.java.more;

import com.mutistic.mybatis.java.more.dto.OneToMoreDto;
import com.mutistic.mybatis.java.more.mapper.OneToMoreMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program MyBatis映射关系：一对多
 * @description
 * @author mutisitic
 * @date 2019年1月21日
 */
public class OneToMoreMain {
	public static void main(String[] args) {
		OneToMoreMapper mapper = SqlSeesionUtil.getMapper(OneToMoreMapper.class);
		PrintUtil.one("1、MyBatis映射关系：一对多");
		
		showByQueryByCollection(mapper);
		SqlSeesionUtil.close();
	}
	private static void showByQueryByCollection(OneToMoreMapper mapper) {
		PrintUtil.one("2、 通过Collection的Select根据外键查询一对多结果集：");
		OneToMoreDto dto = mapper.queryByCollection(111111l);
		PrintUtil.two("2.1、查询结果：", "dto=" + dto);
	}
}
