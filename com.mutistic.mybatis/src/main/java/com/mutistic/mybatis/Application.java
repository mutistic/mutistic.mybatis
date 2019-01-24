package com.mutistic.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

import com.mutistic.mybatis.boot.config.ExcludeFilter;

@SpringBootApplication
//@EnableAutoConfiguration
// 过滤加载为spring bean的类
@ComponentScan(useDefaultFilters = true, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = ExcludeFilter.class) })
// MyBatis 扫描 Mapper.xml
@MapperScan(basePackages="com.mutistic.mybatis.boot.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
