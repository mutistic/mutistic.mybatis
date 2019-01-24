package com.mutistic.mybatis.boot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutistic.mybatis.boot.model.BizUser;
import com.mutistic.mybatis.boot.model.Pagination;
import com.mutistic.mybatis.boot.service.IBizUserService;

/**
 * @program BizUser Controller
 * @description 
 * @author mutisitic
 * @date 2019年1月24日
 */
@RestController
@RequestMapping("/bizUser/")
public class BizUserController {
	
	/**
	 * 自动注入 IBizUserService
	 */
	@Autowired
	private IBizUserService bizUserService;

	/**
	 * @description 新增数据
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param entity
	 * @return
	 */
	@PostMapping("insertEntity")
	public Long insertEntity(@RequestBody BizUser entity) {
		return bizUserService.insertEntity(entity);
	}
	
	/**
	 * @description 修改数据 
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param entity
	 * @return
	 */
	@PostMapping("updateEntity")
	public Long updateEntity(@RequestBody BizUser entity) {
		return bizUserService.updateEntity(entity);
	}
	
	/**
	 * @description 删除数据 
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param id
	 * @return
	 */
	@DeleteMapping("deleteEntity")
	public Long deleteEntity(Long id) {
		return bizUserService.deleteEntity(id);
	}
	
	/**
	 * @description 根据ID查询数据
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param id
	 * @return
	 */
	@GetMapping("queryById")
	public BizUser queryById(Long id) {
		return bizUserService.queryById(id);
	}
	
	/**
	 * @description 查询数据集合 
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param param
	 * @return
	 */
	@PutMapping("queryList")
	public List<BizUser> queryList(@RequestBody Map<String, Object> param) {
		return bizUserService.queryList(param);
	}
	
	/**
	 * @description 分页查询数据集合
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param param
	 * @return
	 */
	@PutMapping("queryPage")
	public Pagination<BizUser> queryPage(@RequestBody Map<String, Object> param) {
		return bizUserService.queryPage(param);
	}
	
}
