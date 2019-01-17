package com.mutistic.mybatis.java.insert;

import java.util.Date;

import com.mutistic.mybatis.java.insert.mapper.InsertMapper;
import com.mutistic.mybatis.java.model.BizBuyAddress;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program MyBatis测试新增
 * @description
 * @author mutisitic
 * @date 2019年1月17日
 */
public class InsertMain {
	public static void main(String[] args) {
		InsertMapper mapper = SqlSeesionUtil.getMapper(InsertMapper.class);

		PrintUtil.one("1、MyBatis测试新增");
		showByStatic(mapper);
		showByDynamic(mapper);

		SqlSeesionUtil.close();
	}

	/**
	 * @description 2、新增静态数据
	 * @author mutisitic
	 * @date 2019年1月17日
	 * @param mapper
	 */
	private static void showByStatic(InsertMapper mapper) {
		PrintUtil.one("2、新增静态数据");

		Long result = mapper.insertByStatic();
		SqlSeesionUtil.commit();
		PrintUtil.two("2.1、新增静态数据结果：", "result=" + result);
	}

	/**
	 * @description 3、新增动态数据
	 * @author mutisitic
	 * @date 2019年1月17日
	 * @param mapper
	 */
	private static void showByDynamic(InsertMapper mapper) {
		PrintUtil.one("3、新增动态数据：");

		BizBuyAddress entity = new BizBuyAddress();
		entity.setId(System.currentTimeMillis());
		entity.setCityCode("210700");
		entity.setConsigneeAddress("testAddress");
		entity.setConsigneeMobile("13600000000");
		entity.setConsigneeName("test");
		entity.setCountyCode("210781");
		entity.setCreateBy(99999l);
		entity.setCreateTime(new Date());
		entity.setEnable(0);
		entity.setIsDefault(1);
		entity.setProvinceCode("210000");
		entity.setRemark("testRemark");
		entity.setUpdateBy(entity.getCreateBy());
		entity.setUpdateTime(entity.getCreateTime());
		entity.setUserId(111111l);
		entity.setVersionNo(0);

		PrintUtil.two("3.1、准备动态数据：", "entity=" + entity);
		Long result2 = mapper.insertByDynamic(entity);
		SqlSeesionUtil.commit();
		PrintUtil.two("3.2、新增动态数据结果：", "result=" + result2);
	}

}
