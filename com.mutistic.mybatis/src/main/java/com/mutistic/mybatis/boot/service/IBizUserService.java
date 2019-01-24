package com.mutistic.mybatis.boot.service;

import java.util.List;
import java.util.Map;

import com.mutistic.mybatis.boot.model.BizUser;
import com.mutistic.mybatis.boot.model.Pagination;

/**
 * @program IBizUserService 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月24日
 */
public interface IBizUserService {

	/**
	 * @description 新增数据
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param entity
	 * @return
	 */
	Long insertEntity(BizUser entity);
	
	/**
	 * @description 修改数据 
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param entity
	 * @return
	 */
	Long updateEntity(BizUser entity);
	
	/**
	 * @description 删除数据 
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param id
	 * @return
	 */
	Long deleteEntity(Long id);
	
	/**
	 * @description 根据ID查询数据
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param id
	 * @return
	 */
	BizUser queryById(Long id);
	
	/**
	 * @description 查询数据集合 
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param param
	 * @return
	 */
	List<BizUser> queryList(Map<String, Object> param);
	
	/**
	 * @description 查询数据id集合
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param param
	 * @return
	 */
	List<Long> selectIds(Map<String, Object> param);
	
	/**
	 * @description 分页查询数据集合
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param param
	 * @return
	 */
	Pagination<BizUser> queryPage(Map<String, Object> param);

}
