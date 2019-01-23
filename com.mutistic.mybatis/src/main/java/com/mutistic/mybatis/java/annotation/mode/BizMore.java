package com.mutistic.mybatis.java.annotation.mode;

/**
 * @program 配合关系映射 
 * @description 
 * @author mutisitic
 * @date 2019年1月23日
 */
public class BizMore {
	private Long id;
	private Long annotationId;
	private String remark;
	private BizAnnotation bizAnnotation;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAnnotationId() {
		return annotationId;
	}
	public void setAnnotationId(Long annotationId) {
		this.annotationId = annotationId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BizAnnotation getBizAnnotation() {
		return bizAnnotation;
	}
	public void setBizAnnotation(BizAnnotation bizAnnotation) {
		this.bizAnnotation = bizAnnotation;
	}
	@Override
	public String toString() {
		return "BizMore [id=" + id + ", annotationId=" + annotationId + ", remark=" + remark + "]";
	}
}
