package com.mutistic.mybatis.java.update.mapper;

import com.mutistic.mybatis.java.model.BizAddress;

/**
 * @program UpdateMapper 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月21日
 */
public interface UpdateMapper {
	
	/**
	 * @description 直接修改数据
	 * @author mutisitic
	 * @date 2019年1月21日
	 * @param entity
	 * @return
	 */
	Long updateEntity(BizAddress entity);
	
	/**
	 * @description 当字段不为null时修改数据
	 * @author mutisitic
	 * @date 2019年1月21日
	 * @param entity
	 * @return
	 */
	Long updateByNotNull(BizAddress entity);
}
