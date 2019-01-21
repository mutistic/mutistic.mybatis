package com.mutistic.mybatis.java.one.mapper;

import com.mutistic.mybatis.java.one.dto.OneToOneDto;

/**
 * @program SelectMapper 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月17日
 */
public interface OneToOneMapper {
	
	/**
	 * @description 通过ResultMap映射查询结果集
	 * @author mutisitic
	 * @date 2019年1月21日
	 * @param id
	 * @return
	 */
	OneToOneDto queryByResultMap(Long id);
	
	/**
	 * @description 通过association的ResultMap属性映射查询结果集 
	 * @author mutisitic
	 * @date 2019年1月21日
	 * @param id
	 * @return
	 */
	OneToOneDto queryByAssociationResultMap(Long id);
	
	/**
	 * @description 通过association的Select根据外键查询结果集 
	 * @author mutisitic
	 * @date 2019年1月21日
	 * @param id
	 * @return
	 */
	OneToOneDto queryByAssociationSelect(Long id);
}
