package com.mutistic.mybatis.java.pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mutistic.mybatis.java.model.BizAddress;
import com.mutistic.mybatis.java.pagination.mapper.PaginationMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program MyBatis分页查询
 * @description
 * @author mutisitic
 * @date 2019年1月23日
 */
public class PaginationMain {
	public static void main(String[] args) {
		PaginationMapper mapper = SqlSeesionUtil.getMapper(PaginationMapper.class);
		PrintUtil.one("1、 MyBatis分页查询");

		showByQueryByLogicPaging(mapper);
		shwoByQueryByDBPaging(mapper);
		shwoByQueryByJavaPaging(mapper);
		SqlSeesionUtil.close();
	}
	private static void showByQueryByLogicPaging(PaginationMapper mapper) {
		PrintUtil.one("2、 逻辑分页查询（通过RowBounds查询到所有的数据后在内存中分页，不建议使用）");
		RowBounds rowBounds = new RowBounds(0, 3);
		List<BizAddress> entityList = mapper.queryByLogicPaging(rowBounds);
		PrintUtil.two("2.1、查询结果", "entitySize=" + entityList.size() + ", entityList=" + entityList);
	}
	private static void shwoByQueryByDBPaging(PaginationMapper mapper) {
		PrintUtil.one("3、 Mysql物理分页查询");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("consigneeNameLike", "test");
		param.put("limit", 3); // 每页内容数(可理解为： pageSize)
		param.put("offset", 0); // 当前页数 (可理解未：pageIndex)
		List<BizAddress> entityList = mapper.queryByDBPaging(param);
		PrintUtil.two("3.1、查询结果", "entitySize=" + entityList.size() + ", entityList=" + entityList);
	}
	private static void shwoByQueryByJavaPaging(PaginationMapper mapper) {
		PrintUtil.one("4、 Java内存假分页");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("consigneeNameLike", "test");
		List<BizAddress> entityList = mapper.queryByDBPaging(param);
		PrintUtil.two("4.1、查询结果", "entitySize=" + entityList.size() + ", entityList=" + entityList);

		int limit = 3;
		int offset = 1;
		int toIndex = limit * (offset + 1); // 预估要截取的集合的toIndex
		List<BizAddress> pageList = entityList.subList(limit * offset,
				entityList.size() > toIndex ? toIndex : entityList.size());
		PrintUtil.two("4.2、 Java内存假分页结果", "pageSize=" + pageList.size() + ", pageList=" + entityList);
	}
}
