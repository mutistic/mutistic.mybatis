package com.mutistic.mybatis.java.select.mapper;

import java.util.List;
import java.util.Map;

import com.mutistic.mybatis.java.model.BizBuyAddress;

/**
 * @program SelectMapper 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月17日
 */
public interface SelectMapper {
	
	/**
	 * @description 根据ID查询数据 
	 * @author mutisitic
	 * @date 2019年1月18日
	 * @param id
	 * @return
	 */
	BizBuyAddress queryById(Long id);

	/**
	 * @description 根据实体查询集合
	 * @author mutisitic
	 * @date 2019年1月17日
	 * @param param
	 * @return
	 */
	List<BizBuyAddress> queryList(BizBuyAddress param);

	/**
	 * @description 分页查询-查询总条数
	 * @author mutisitic
	 * @date 2019年1月18日
	 * @param params
	 * @return
	 */
	List<Long> selectCount(Map<String, Object> params);
	
	/**
	 * @description 分页查询-查询当前内容数
	 * @author mutisitic
	 * @date 2019年1月18日
	 * @param params
	 * @return
	 */
	List<BizBuyAddress> queryPage(Map<String, Object> params);

	
}
