package com.mutistic.mybatis.boot.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutistic.mybatis.boot.mapper.BizUserMapper;
import com.mutistic.mybatis.boot.model.BizUser;
import com.mutistic.mybatis.boot.model.Pagination;
import com.mutistic.mybatis.boot.service.IBizUserService;

/**
 * @program IBizUserService 接口实现类
 * @description 
 * @author mutisitic
 * @date 2019年1月24日
 */
@Service("BizUserService")
public class BizUserServiceImpl implements IBizUserService {

	/**
	 * 自动注入 BizUserMapper
	 */
	@Autowired
	private BizUserMapper bizUserMapper;
	
	/**
	 * @description 新增数据
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param entity
	 * @return
	 */
	@Override
	public Long insertEntity(BizUser entity) {
		entity.setId(System.currentTimeMillis());
		entity.setCreateTime(new Date());
		entity.setUpdateTime(entity.getCreateTime());
		return bizUserMapper.insertEntity(entity);
	}
	
	/**
	 * @description 修改数据 
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param entity
	 * @return
	 */
	@Override
	public Long updateEntity(BizUser entity) {
		entity.setUpdateTime(new Date());
		return bizUserMapper.updateEntity(entity);
	}
	
	/**
	 * @description 删除数据 
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param id
	 * @return
	 */
	@Override
	public Long deleteEntity(Long id) {
		return bizUserMapper.deleteEntity(id);
	}
	
	/**
	 * @description 根据ID查询数据
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param id
	 * @return
	 */
	@Override
	public BizUser queryById(Long id) {
		return bizUserMapper.queryById(id);
	}
	
	/**
	 * @description 查询数据集合 
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param param
	 * @return
	 */
	@Override
	public List<BizUser> queryList(Map<String, Object> param) {
		return bizUserMapper.queryList(param);
	}
	
	/**
	 * @description 查询数据id集合
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param param
	 * @return
	 */
	@Override
	public List<Long> selectIds(Map<String, Object> param) {
		return bizUserMapper.selectIds(param);
	}
	
	/**
	 * @description 分页查询数据集合
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param param
	 * @return
	 */
	@Override
	public Pagination<BizUser> queryPage(Map<String, Object> param) {
		Pagination<BizUser> page = new Pagination<BizUser>();
		
		// 查询数据总数
		List<Long> ids = selectIds(param);
		if(ids == null || ids.isEmpty()) {
			return page;
		}
		
		// 处理分页参数
		if(!param.containsKey("limit")) {
			param.put("limit", 10);
		}
		if(!param.containsKey("offset")) {
			param.put("offset", 0);
		}
		Integer limit = (Integer) param.get("limit"); // 表示每次返回的数据条数
		Integer offset = (Integer) param.get("offset"); //表示从该参数的下一条数据开始
		param.put("offset", offset*limit);
		
		List<BizUser> entityList = bizUserMapper.queryList(param);
		
		// 封装成分页对象
		page.setTotal(Long.valueOf(ids.size()));
		page.setPages(offset++);
		page.setSize(entityList.size());
		page.setRecords(entityList);
		
		int current = (int)(page.getTotal() / limit);
		if(page.getTotal() % limit != 0) {
			current++;
		}
		page.setCurrent(current);
	
		return page;
	}

}
