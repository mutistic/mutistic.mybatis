package com.mutistic.mybatis.boot.config;

import java.io.IOException;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program 排除加载类的TypeFilter
 * @description 
 * @author mutisitic
 * @date 2019年1月24日
 */
public class ExcludeFilter implements TypeFilter {
	/**
	 * @description 匹配规则
	 * @author mutisitic
	 * @date 2019年1月24日
	 * @param reader
	 * @param factory
	 * @return
	 * @throws IOException
	 * @see org.springframework.core.type.filter.TypeFilter#match(org.springframework.core.type.classreading.MetadataReader, org.springframework.core.type.classreading.MetadataReaderFactory)
	 */
	@Override
	public boolean match(MetadataReader reader, MetadataReaderFactory factory) throws IOException {
		ClassMetadata meader = reader.getClassMetadata();
		if(meader != null && (meader.getClassName().contains("com.mutistic.mybatis.java")
				|| meader.getClassName().contains("com.mutistic.mybatis.generate"))) {
			PrintUtil.three("ExcludeFilter：过滤", meader.getClassName());
			return true;
		}
		
		return false;
	}

}
