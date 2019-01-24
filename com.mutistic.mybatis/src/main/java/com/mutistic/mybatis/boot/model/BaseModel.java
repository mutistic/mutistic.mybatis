package com.mutistic.mybatis.boot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program Model父类 
 * @description 
 * @author mutisitic
 * @date 2019年1月18日
 */
@SuppressWarnings("serial")
public class BaseModel implements Serializable {
	/** 主键 */
	private Long id;
	/** 主键集合 */
	private List<Long> ids;
	/** 创建人 */
	private Long createBy;
	/** 创建时间 */
	private Date createTime;
	/** 修改人 */
	private Long updateBy;
	/** 修改时间 */
	private Date updateTime;
	/** 是否逻辑删除：0-未删除，1-已删除 */
	private Integer enable;
	/** 备注 */
	private String remark;
	/**  版本号 */
	private Integer versionNo;
	/** 排序字段 */
	private String orderBy;
	/** 排序规则 */
	private String sortAsc;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getSortAsc() {
		return sortAsc;
	}
	public void setSortAsc(String sortAsc) {
		this.sortAsc = sortAsc;
	}
}
