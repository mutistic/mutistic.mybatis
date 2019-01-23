package com.mutistic.mybatis.java.other.mapper;

import java.util.List;

import com.mutistic.mybatis.java.other.BizTest;

/**
 * @program OtherMapper接口
 * @description 
 * @author mutisitic
 * @date 2019年1月23日
 */
public interface OtherMapper {

	/**
	 * @description blob/clob数据类型的新增
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param entity
	 * @return
	 */
	Long insertEntity(BizTest entity);
	
	/**
	 * @description blob/clob数据类型的查询
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param id
	 * @return
	 */
	BizTest queryById(Long id);
	
	/**
	 * @description 使用多个参数查询数据（不建议使用）
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param id
	 * @param longClob
	 * @return
	 */
	List<BizTest> queryByParams(Long id, String longClob);
	
}
