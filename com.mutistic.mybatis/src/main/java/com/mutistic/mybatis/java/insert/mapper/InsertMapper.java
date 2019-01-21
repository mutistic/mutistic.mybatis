package com.mutistic.mybatis.java.insert.mapper;

import com.mutistic.mybatis.java.model.BizAddress;

/**
 * @program InsertMapper 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月17日
 */
public interface InsertMapper {

	/**
	 * @description 新增静态数据
	 * @author mutisitic
	 * @date 2019年1月17日
	 * @return
	 */
	Long insertByStatic();

	/**
	 * @description 新增动态数据 
	 * @author mutisitic
	 * @date 2019年1月17日
	 * @param entity
	 * @return
	 */
	Long insertByDynamic(BizAddress entity);
}
