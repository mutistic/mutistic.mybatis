package com.mutistic.mybatis.java.select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mutistic.mybatis.java.model.BizBuyAddress;
import com.mutistic.mybatis.java.model.Pagination;
import com.mutistic.mybatis.java.select.mapper.SelectMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program  MyBatis查询数据
 * @description
 * @author mutisitic
 * @date 2019年1月17日
 */
public class SelectMain {
	public static void main(String[] args) {
		SelectMapper mapper = SqlSeesionUtil.getMapper(SelectMapper.class);
		PrintUtil.one("1、 MyBatis查询数据");
		showByQueryById(mapper);
		showByQueryList(mapper);
		showByQueryPage(mapper);
		SqlSeesionUtil.close();
	}

	private static void showByQueryById(SelectMapper mapper) {
		PrintUtil.one("2、根据ID查询数据： ");
		BizBuyAddress entity = mapper.queryById(1547713057l);
		PrintUtil.two("2.1、查询结果：", "entity=" + entity);
	}

	private static void showByQueryList(SelectMapper mapper) {
		PrintUtil.one("3、根据实体查询集合：");

		BizBuyAddress params = new BizBuyAddress();
		params.setId(1547713057l);
		List<BizBuyAddress> entityList = mapper.queryList(params);
		PrintUtil.two("3.1、查询结果：", "entityList=" + entityList);
	}

	private static void showByQueryPage(SelectMapper mapper) {
		PrintUtil.one("4、根据Map分页：");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("consigneeNameLike", "test");
		List<Long> idList = mapper.selectCount(params);
		PrintUtil.two("4.1、查询总条数（id集合）：", "idList=" + idList);
		PrintUtil.println();

		Map<String, Object> pageParam = new HashMap<String, Object>();
		pageParam.put("offset", 0);
		pageParam.put("limit", 10);
		pageParam.put("ids", idList);
		List<BizBuyAddress> resultList = mapper.queryPage(pageParam);
		PrintUtil.two("4.2、分页查询结果（集合大小）：", "idList=" + resultList.size());
		PrintUtil.println();

		Integer offset = (Integer) pageParam.get("offset");
		Integer limit = (Integer) pageParam.get("limit");
		Pagination<BizBuyAddress> page = new Pagination<BizBuyAddress>();
		page.setTotal(Long.valueOf(idList.size()));
		page.setPages(offset);
		page.setSize(resultList.size());
		page.setRecords(resultList);
		
		int current = (int)(page.getTotal() / limit);
		if(page.getTotal() % limit != 0) {
			current++;
		}
		page.setCurrent(current);
		PrintUtil.two("4.3、封装成分页对象：", "Pagination=" + page);
	}

}
