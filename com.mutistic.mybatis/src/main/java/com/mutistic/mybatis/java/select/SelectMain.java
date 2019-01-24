package com.mutistic.mybatis.java.select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mutistic.mybatis.java.model.BizAddress;
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
		BizAddress entity = mapper.queryById(1547713057l);
		PrintUtil.two("2.1、查询结果：", "entity=" + entity);
	}

	private static void showByQueryList(SelectMapper mapper) {
		PrintUtil.one("3、根据实体查询集合：");

		BizAddress params = new BizAddress();
		params.setId(1547713057l);
		List<BizAddress> entityList = mapper.queryList(params);
		PrintUtil.two("3.1、查询结果：", "entityList=" + entityList);
	}

	private static void showByQueryPage(SelectMapper mapper) {
		PrintUtil.one("4、根据Map分页：");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("consigneeNameLike", "test");
		params.put("limit", 3);
		params.put("offset", 0);
		List<Long> idList = mapper.selectCount(params);
		PrintUtil.two("4.1、查询总条数（id集合）：", "idList=" + idList);
		PrintUtil.println();

		Integer limit = (Integer) params.get("limit"); // 表示每次返回的数据条数
		Integer offset = (Integer) params.get("offset"); //表示从该参数的下一条数据开始
		params.put("offset", offset*limit);
		
		List<BizAddress> resultList = mapper.queryPage(params);
		PrintUtil.two("4.2、分页查询结果（集合大小）：", "idList=" + resultList.size());
		PrintUtil.println();

		Pagination<BizAddress> page = new Pagination<BizAddress>();
		page.setTotal(Long.valueOf(idList.size()));
		page.setPages(offset++);
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
