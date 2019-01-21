package com.mutistic.mybatis.java.more.mapper;

import com.mutistic.mybatis.java.more.dto.OneToMoreDto;

/**
 * @program OneToMoreMapper 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月21日
 */
public interface OneToMoreMapper {

	/**
	 * @description 通过Collection的Select根据外键查询一对多结果集 
	 * @author mutisitic
	 * @date 2019年1月21日
	 * @param id
	 * @return
	 */
	OneToMoreDto queryByCollection(Long id);
	
}
