package com.mutistic.mybatis.java.provider.mapper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.mutistic.mybatis.java.annotation.mode.BizAnnotation;

/**
 * @program SqlProvider动态拼接SQL实现类
 * @description
 * @author mutisitic
 * @date 2019年1月23日
 */
public class BizAnnotationSqlProvider {

	/**
	 * @description 新增数据的拼接SQL方法
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param entity
	 * @return
	 */
	public String insertEntity(final BizAnnotation entity) {
		return new SQL() {
			{
				INSERT_INTO("biz_annotation");
				VALUES("id_", entity.getId() + "");
				VALUES("name_", "'" + entity.getName() + "'");
				VALUES("age_", entity.getAge() + "");
			}
		}.toString();
	}

	/**
	 * @description 修改数据的拼接SQL方法
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param entity
	 * @return
	 */
	public String updateEntity(final BizAnnotation entity) {
		return new SQL() {
			{
				UPDATE("biz_annotation");
				if (entity.getName() != null) {
					SET("name_ = '" + entity.getName() + "'");
				}
				if (entity.getAge() != null) {
					SET("age_ = " + entity.getAge());
				}
				WHERE("id_ = " + entity.getId());
			}
		}.toString();
	}

	/**
	 * @description 删除数据的拼接SQL方法
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @return
	 */
	public String deleteEntity() {
		return new SQL() {
			{
				DELETE_FROM("biz_annotation");
				WHERE("id_ = #{id}");
			}
		}.toString();
	}

	/**
	 * @description 根据ID查询数据的拼接SQL方法
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param id
	 * @return
	 */
	public String queryById(final Long id) {
		return new SQL() {
			{
				SELECT("id_, name_, age_");
				FROM("biz_annotation");
				if (null != id) {
					WHERE("id_ = #{id}");
				}
			}
		}.toString();
	}

	/**
	 * @description 动态查询数据的拼接SQL方法
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param param
	 * @return
	 */
	public String queryList(final Map<String, Object> param) {
		return new SQL() {
			{
				SELECT("id_, name_, age_");
				FROM("biz_annotation");
				if (param != null && param.size() > 0) {
					StringBuffer sb = new StringBuffer();
					if (param.containsKey("name")) {
						sb.append("AND name_ = " + param.get("name"));
					}
					if (param.containsKey("age")) {
						sb.append("AND age_ = " + param.get("age"));
					}
					WHERE(sb.toString().replaceFirst("AND", ""));
				}
			}
		}.toString();
	}
}
