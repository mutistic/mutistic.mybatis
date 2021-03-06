package com.mutistic.mybatis.java.update;

import java.util.Date;

import com.mutistic.mybatis.java.model.BizAddress;
import com.mutistic.mybatis.java.update.mapper.UpdateMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program  MyBatis修改数据
 * @description
 * @author mutisitic
 * @date 2019年1月21日
 */
public class UpdateMain {
	public static void main(String[] args) {
		UpdateMapper mapper = SqlSeesionUtil.getMapper(UpdateMapper.class);
		PrintUtil.one("1、 MyBatis修改数据");
		
		BizAddress entity = new BizAddress();
		entity.setId(1029214969835257858l);
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
		entity.setVersionNo(1);
		
		showByUpdateEntity(mapper, entity);
		showByUpdateByNotNull(mapper, entity);
		SqlSeesionUtil.close();
	}

	private static void showByUpdateEntity(UpdateMapper mapper, BizAddress entity) {
		PrintUtil.one("2、直接修改数据： ");
		Long result = mapper.updateEntity(entity);
		PrintUtil.two("2.1、修改结果", "result="+result);
		SqlSeesionUtil.commit();
	}

	private static void showByUpdateByNotNull(UpdateMapper mapper, BizAddress entity) {
		PrintUtil.one("3、当字段不为null时修改数据： ");
		entity.setRemark("");
		entity.setVersionNo(2);
		Long result = mapper.updateByNotNull(entity);
		PrintUtil.two("3.1、修改结果", "result="+result);
		SqlSeesionUtil.commit();
	}
}
