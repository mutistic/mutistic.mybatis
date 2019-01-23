package com.mutistic.mybatis.java.annotation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mutistic.mybatis.java.annotation.mode.BizAnnotation;

/**
 * @program AnnotationMapper接口
 * @description 
 * @author mutisitic
 * @date 2019年1月23日
 */
public interface AnnotationMapper {
	
	/**
	 * @description 使用@Insert注解实现数据新增
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO biz_annotation(id_, name_, age_) VALUES(#{id}, #{name}, #{age})")
	Long insertEntity(BizAnnotation entity);

	/**
	 * @description 使用@Update注解实现数据修改
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param entity
	 * @return
	 */
	@Update("UPDATE biz_annotation SET name_ = #{name}, age_ = #{age} WHERE id_ = #{id}")
	Long updateEntity(BizAnnotation entity);

	/**
	 * @description 使用@Delete注解实现数据删除
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM biz_annotation WHERE id_ = #{id}")
	Long deleteEntity(Long id);

	/**
	 * @description 使用@Selete注解实现数据查询
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param id
	 * @return
	 */
	@Select("SELECT id_, name_, age_ FROM biz_annotation WHERE id_ = #{id}")
	// 使用@Results映射查询结果
	@Results(id = "annotationResult", value={
		@Result(id=true, column="id_", property="id"),
		@Result(column="name_", property="name"),
		@Result(column="age_", property="age")
	})
	BizAnnotation queryById(Long id);

	/**
	 * @description  使用@Selete注解和<script>实现动态查询
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param param
	 * @return
	 */
	@Select({ 
		"<script>", // 使用<script>实现动态SQL
		"SELECT id_, name_, age_ FROM biz_annotation", 
		"<where>",
		"<if test=\"id!=null\">AND id_= #{id}</if>",
		"<if test=\"name!=null and name != ''\">AND name_ = #{name}</if>",
		"<if test=\"age!=null\">AND age_ = #{age}</if>",
		"</where>",
		"</script>" 
		})
	// 使用@ResultMap 绑定声明的@Results，实现复用
	@ResultMap("annotationResult")
	List<BizAnnotation> queryList(Map<String, Object> param);
}
