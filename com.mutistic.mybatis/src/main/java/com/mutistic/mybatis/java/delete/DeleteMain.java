package com.mutistic.mybatis.java.delete;

import com.mutistic.mybatis.java.delete.mapper.DeleteMapper;
import com.mutistic.mybatis.java.model.BizBuyAddress;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program MyBatis删除数据
 * @description
 * @author mutisitic
 * @date 2019年1月21日
 */
public class DeleteMain {
	public static void main(String[] args) {
		DeleteMapper mapper = SqlSeesionUtil.getMapper(DeleteMapper.class);
		PrintUtil.one("1、 MyBatis删除数据");
		showByDeleteEntity(mapper);
		showByDeleteById(mapper);
		SqlSeesionUtil.close();
	}

	private static void showByDeleteEntity(DeleteMapper mapper) {
		PrintUtil.one("2、根据实体删除数据：");
		BizBuyAddress entity = new BizBuyAddress();
		entity.setId(1029214969835257858l);
		Long result = mapper.deleteEntity(entity);
		PrintUtil.two("2.1、删除结果", "result="+ result);
	}
	
	private static void showByDeleteById(DeleteMapper mapper) {
		PrintUtil.one("3、根据ID删除数据");
		Long result = mapper.deleteById(1547720793414l);
		PrintUtil.two("3.1、删除结果", "result="+ result);
	}
}
