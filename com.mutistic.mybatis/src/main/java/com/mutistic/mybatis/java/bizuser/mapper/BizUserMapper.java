package com.mutistic.mybatis.java.bizuser.mapper;

import com.mutistic.mybatis.java.model.BizUser;

/**
 * @program BizUserMapper 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月17日
 */
public interface BizUserMapper {
	
	/**
	 * @description 根据ID查询数据 
	 * @author mutisitic
	 * @date 2019年1月21日
	 * @param id
	 * @return
	 */
	BizUser queryById(Long id);
	
}
