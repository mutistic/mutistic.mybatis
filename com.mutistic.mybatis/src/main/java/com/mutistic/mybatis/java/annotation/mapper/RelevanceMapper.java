package com.mutistic.mybatis.java.annotation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mutistic.mybatis.java.annotation.mode.BizAnnotation;
import com.mutistic.mybatis.java.annotation.mode.BizMore;

/**
 * @program 使用注解实现关联查询
 * @description 
 * @author mutisitic
 * @date 2019年1月23日
 */
public interface RelevanceMapper {
	
	/**
	 * @description 使用@One注解实现一对一关联查询
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param id
	 * @return
	 */
	@Select("SELECT id_, annotation_id, remark FROM biz_more WHERE id_ = #{id}")
	@Results({
		@Result(id=true, column="id_", property="id"),
		@Result(column="annotation_id", property="annotationId"),
		@Result(column="remark", property="remark"),
		// 使用@One实现一对一关联查询
		@Result(column="annotation_id", property="bizAnnotation",
			one=@One(select="com.mutistic.mybatis.java.annotation.mapper.AnnotationMapper.queryById"))
	})
	BizMore queryByOneToOne(Long id);
	
	/**
	 * @description 使用@Many注解实现一对多关联查询
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param id
	 * @return
	 */
	@Select("SELECT id_, name_, age_ FROM biz_annotation WHERE id_ = #{id}")
	@Results(id = "annotationResult", value={
			@Result(id=true, column="id_", property="id"),
			@Result(column="name_", property="name"),
			@Result(column="age_", property="age"),
			// 使用@Many注解实现一对多关联查询
			@Result(column="id_", property="bizMoreList",
				many=@Many(select="com.mutistic.mybatis.java.annotation.mapper.RelevanceMapper.queryByAnnontationId"))
		})
	BizAnnotation queryByOneToMore(Long id);
	
	@Select("SELECT id_, annotation_id, remark FROM biz_more WHERE annotation_id = #{annotationId}")
	@Results({
		@Result(id=true, column="id_", property="id"),
		@Result(column="annotation_id", property="annotationId"),
		@Result(column="remark", property="remark"),
	})
	List<BizMore> queryByAnnontationId(Long annotationId);
}
