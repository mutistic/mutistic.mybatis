package com.mutistic.mybatis.java.bizuser;

import com.mutistic.mybatis.java.bizuser.mapper.BizUserMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program  测试BizUser
 * @description
 * @author mutisitic
 * @date 2019年1月17日
 */
public class BizUserMain {
	public static void main(String[] args) {
		BizUserMapper mapper = SqlSeesionUtil.getMapper(BizUserMapper.class);
		PrintUtil.one("1、 MyBatis查询数据");
		
		showByQueryDtoByResult(mapper);
		SqlSeesionUtil.close();
	}

	private static void showByQueryDtoByResult(BizUserMapper mapper) {
		
		System.out.println(mapper.queryById(111111l));
		
	}


}
