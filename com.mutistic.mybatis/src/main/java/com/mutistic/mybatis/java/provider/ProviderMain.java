package com.mutistic.mybatis.java.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mutistic.mybatis.java.annotation.mode.BizAnnotation;
import com.mutistic.mybatis.java.provider.mapper.ProviderMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program MyBatis使用@SelectProvider等注解实现CRUD
 * @description 
 * @author mutisitic
 * @date 2019年1月23日
 */
public class ProviderMain {
	public static void main(String[] args) {
		ProviderMapper mapper = SqlSeesionUtil.getMapper(ProviderMapper.class);
		PrintUtil.one("1、 MyBatis使用@SelectProvider等注解实现CRUD");

		showByQueryById(mapper);
		showByQueryList(mapper);
		BizAnnotation entity = showByInsert(mapper);
		showByUpdate(mapper, entity);
		showByDelete(mapper);
		SqlSeesionUtil.close();
	}
	private static void showByQueryById(ProviderMapper mapper) {
		PrintUtil.one("2、使用@SeleteProvider注解实现数据查询：");
		
		BizAnnotation entity = mapper.queryById(1548226948040l);
		PrintUtil.two("2.1、查询結果：", entity);
	}
	private static void showByQueryList(ProviderMapper mapper) {
		PrintUtil.one("3、使用@SeleteProvider注解和<script>实现动态查询：");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("age", 18);
		List<BizAnnotation> entityList = mapper.queryList(param);
 		PrintUtil.two("3.1、查询結果：", entityList);
	}
	private static BizAnnotation showByInsert(ProviderMapper mapper) {
		PrintUtil.one("4、使用@InsertProvider注解实现数据新增：");
		
		BizAnnotation entity = new BizAnnotation();
		entity.setId(System.currentTimeMillis());
		entity.setName("test");
		entity.setAge(18);
		
		Long result = mapper.insertEntity(entity);
		PrintUtil.two("4.1、新增结果：", result);
		SqlSeesionUtil.commit();
		return entity;
	}
	private static void showByUpdate(ProviderMapper mapper, BizAnnotation entity) {
		PrintUtil.one("5、使用@UpdateProvider注解实现数据修改：");
		
		entity.setName("张三");
		Long result = mapper.updateEntity(entity);
		PrintUtil.two("5.1、修改结果：", result);
		SqlSeesionUtil.commit();
	}
	private static void showByDelete(ProviderMapper mapper) {
		PrintUtil.one("6、使用@DeleteProvider注解实现数据删除：");
		
		Long result = mapper.deleteEntity(1548225597700l);
		PrintUtil.two("6.1、删除结果：", result);
		SqlSeesionUtil.commit();
	}
}
