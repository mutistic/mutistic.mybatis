package com.mutistic.mybatis.java.dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mutistic.mybatis.java.dynamic.mapper.DynamicMapper;
import com.mutistic.mybatis.java.model.BizUser;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program MyBatis：动态SQL
 * @description 
 * @author mutisitic
 * @date 2019年1月22日
 */
public class DynamicMain {
	public static void main(String[] args) {
		DynamicMapper mapper = SqlSeesionUtil.getMapper(DynamicMapper.class);
		PrintUtil.one("1、 MyBatis动态SQL");

		showByQueryByDynamic(mapper);
		BizUser user = showByQueryByTrim(mapper);
		showByUpdateBySet(mapper, user);
		SqlSeesionUtil.close();
	}
	private static void showByQueryByDynamic(DynamicMapper mapper) {
		PrintUtil.one("2、动态SQL：WHERE、IF、choose、foreach");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("statusList", new Integer[] {0,1,2});
		param.put("statusType", 1);
		List<BizUser> userList = mapper.queryByDynamic(param);
		PrintUtil.two("2.1、查询结果：", userList);
	}
	private static BizUser showByQueryByTrim(DynamicMapper mapper) {
		PrintUtil.one("3、动态SQL：Trim");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", 111111l);
		List<BizUser> userList = mapper.queryByTrim(param);
		PrintUtil.two("3.1、查询结果：", userList);
		return userList.get(0);
	}
	private static void showByUpdateBySet(DynamicMapper mapper, BizUser entity) {
		PrintUtil.one("4、动态SQL：Set");
		
		entity.setRemark("");
		entity.setPassword(null);
		Long result = mapper.updateBySet(entity);
		PrintUtil.two("4.1、修改结果：", result);
	}
}
