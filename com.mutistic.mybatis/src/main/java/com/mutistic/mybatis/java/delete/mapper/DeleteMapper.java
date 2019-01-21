package com.mutistic.mybatis.java.delete.mapper;

import com.mutistic.mybatis.java.model.BizBuyAddress;

/**
 * @program DeleteMapper 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月21日
 */
public interface DeleteMapper {

	/**
	 * @description 根据实体删除数据
	 * @author mutisitic
	 * @date 2019年1月21日
	 * @param entity
	 * @return
	 */
	Long deleteEntity(BizBuyAddress entity);

	/**
	 * @description 根据ID删除数据 
	 * @author mutisitic
	 * @date 2019年1月21日
	 * @param id
	 * @return
	 */
	Long deleteById(Long id);
		
	
}
