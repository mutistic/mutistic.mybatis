package com.mutistic.mybatis.java.provider.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.mutistic.mybatis.java.annotation.mode.BizAnnotation;

/**
 * @program ProviderMapper 接口
 * @description 
 * @author mutisitic
 * @date 2019年1月23日
 */
public interface ProviderMapper {

	/**
	 * @description 使用@InsertProvider注解实现数据新增
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param entity
	 * @return
	 */
	@InsertProvider(type=BizAnnotationSqlProvider.class, method="insertEntity")
	Long insertEntity(BizAnnotation entity);
	
	/**
	 * @description 使用@UpdateProvider注解实现数据修改
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type=BizAnnotationSqlProvider.class, method="updateEntity")
	Long updateEntity(BizAnnotation entity);
	/**
	 * @description 使用@DeleteProvider注解实现删除
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param id
	 * @return
	 */
	@DeleteProvider(type=BizAnnotationSqlProvider.class, method="deleteEntity")
	Long deleteEntity(Long id);
	
	/**
	 * @description 使用@SelectProvider注解实现查询
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param id
	 * @return
	 */
	@SelectProvider(type=BizAnnotationSqlProvider.class, method="queryById")
	// 非此Mapper定义的resultMap时，需要指定ResultMap全限定名
	@ResultMap("com.mutistic.mybatis.java.annotation.mapper.AnnotationMapper.BizAnnotationResult")
	BizAnnotation queryById(Long id);
	
	/**
	 * @description 使用@SelectProvider注解实现数据集合查询
	 * @author mutisitic
	 * @date 2019年1月23日
	 * @param param
	 * @return
	 */
	@SelectProvider(type=BizAnnotationSqlProvider.class, method="queryList")
	@ResultMap("com.mutistic.mybatis.java.annotation.mapper.AnnotationMapper.BizAnnotationResult")
	List<BizAnnotation> queryList(Map<String, Object> param);
}
