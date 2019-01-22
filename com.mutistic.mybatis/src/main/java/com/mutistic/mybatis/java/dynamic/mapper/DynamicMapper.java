package com.mutistic.mybatis.java.dynamic.mapper;

import java.util.List;
import java.util.Map;

import com.mutistic.mybatis.java.model.BizUser;

/**
 * @program DynamicMapper 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月22日
 */
public interface DynamicMapper {

	/**
	 * @description 动态SQL：WHERE、IF、choose、foreach
	 * @author mutisitic
	 * @date 2019年1月22日
	 * @param param
	 * @return
	 */
	List<BizUser> queryByDynamic(Map<String, Object> param);
	
	/**
	 * @description 动态SQL：Trim
	 * @author mutisitic
	 * @date 2019年1月22日
	 * @param param
	 * @return
	 */
	List<BizUser> queryByTrim(Map<String, Object> param);
	
	/**
	 * @description 动态SQL：Set
	 * @author mutisitic
	 * @date 2019年1月22日
	 * @param entity
	 * @return
	 */
	Long updateBySet(BizUser entity);
	
}
