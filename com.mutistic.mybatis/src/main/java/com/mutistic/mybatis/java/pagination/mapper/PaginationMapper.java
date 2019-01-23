package com.mutistic.mybatis.java.pagination.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mutistic.mybatis.java.model.BizAddress;

public interface PaginationMapper {
	/**
	 * @description 逻辑分页（通过RowBounds查询到所有的数据后在内存中分页，不建议使用）
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param bounds
	 * @return
	 */
	List<BizAddress> queryByLogicPaging(RowBounds bounds);

	/**
	 * @description MySql分页查询 
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param param
	 * @return
	 */
	List<BizAddress> queryByDBPaging(Map<String, Object> param);
	
}
