package com.mutistic.mybatis.java.model;

import java.io.Serializable;
import java.util.List;

/**
 * @program 分页对象
 * @description 
 * @author mutisitic
 * @date 2019年1月18日
 * @param <T>
 */
@SuppressWarnings("serial")
public class Pagination<T> implements Serializable {
	/** 总条数 */
	private Long total;
	/** 总页数 */
	private Integer current;
	/** 当前页条数 */
	private Integer size;
	/** 当前页 */
	private Integer pages;
	/** 当前页数据 */
	private List<T> records;
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("Pagination [total=" + total + ", current=" + current + ", size=" + size + ", pages=" + pages
				+ ", records=\n{");
		for (T t : records) {
			buffer.append(t+"\n");
		}
		buffer.substring(0, buffer.length()-2);
		buffer.append("}]");
		return buffer.toString();
	}
	
}
