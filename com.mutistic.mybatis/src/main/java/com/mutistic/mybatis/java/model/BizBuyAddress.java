package com.mutistic.mybatis.java.model;

/**
 * @program 收货地址 
 * @description 
 * @author mutisitic
 * @date 2019年1月18日
 */
@SuppressWarnings("serial")
public class BizBuyAddress extends BaseModel {
	/** 用户ID */
	private Long userId;
	/** 收货人 */
	private String consigneeName;
	/** 收货手机号 */
	private String consigneeMobile;
	/** 收货地址 */
	private String consigneeAddress;
	/** 省份编码 */
	private String provinceCode;
	/** 城市编码 */
	private String cityCode;
	/** 区县编码 */
	private String countyCode;
	/** 是否默认地址：0-非默认，1-默认 */
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
				+ ", orderBy=" + super.getOrderBy() + ", sortAsc=" + super.getSortAsc();
	}
}
