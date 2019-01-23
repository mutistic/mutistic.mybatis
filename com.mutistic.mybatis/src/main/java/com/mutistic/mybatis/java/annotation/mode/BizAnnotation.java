package com.mutistic.mybatis.java.annotation.mode;

import java.util.List;

/**
 * @program 配合注解 
 * @description 
 * @author mutisitic
 * @date 2019年1月23日
 */
public class BizAnnotation {
	private Long id;
	private String name;
	private Integer age;
	private List<BizMore> bizMoreList;
	
	public List<BizMore> getBizMoreList() {
		return bizMoreList;
	}
	public void setBizMoreList(List<BizMore> bizMoreList) {
		this.bizMoreList = bizMoreList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "BizAnnotation [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
}
