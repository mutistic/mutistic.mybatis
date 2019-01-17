package com.mutistic.mybatis.java.model;

@SuppressWarnings("serial")
public class BizBuyAddress extends BaseModel {
	private Long userId;
	private String consigneeName;
	private String consigneeMobile;
	private String consigneeAddress;
	private String provinceCode;
	private String cityCode;
	private String countyCode;
	private Integer isDefault;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "BizBuyAddress [id=" + super.getId() + ", userId=" + userId + ", consigneeName=" + consigneeName
				+ ", consigneeMobile=" + consigneeMobile + ", consigneeAddress=" + consigneeAddress + ", provinceCode="
				+ provinceCode + ", cityCode=" + cityCode + ", countyCode=" + countyCode + ", isDefault=" + isDefault
				+ ", createBy=" + super.getCreateBy() + ", createTime=" + super.getCreateTime() + ", updateBy="
				+ super.getUpdateBy() + ", updateTime=" + super.getUpdateTime() + "]" + ", versionNo="
				+ super.getVersionNo() + ", enable=" + super.getEnable() + ", remark=" + super.getRemark()
				+ ", orderBy=" + super.getOrderBy() + ", sortAsc=" + super.getSortAsc() + ", limit=" + super.getLimit()
				+ ", offset=" + super.getOffset();
	}
}
