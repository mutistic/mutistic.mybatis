package com.mutistic.mybatis.boot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mutistic.mybatis.boot.model.BizUser;

/**
 * @program Mapper 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月24日
 */
//@Mapper // 标识此类是一个Mapper接口
public interface BizUserMapper {

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
	List<BizUser> queryPage(Map<String, Object> param);
}
