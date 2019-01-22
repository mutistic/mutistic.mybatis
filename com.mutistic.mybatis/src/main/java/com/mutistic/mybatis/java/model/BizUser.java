package com.mutistic.mybatis.java.model;

/**
 * @program 用户
 * @description
 * @author mutisitic
 * @date 2019年1月21日
 */
@SuppressWarnings("serial")
public class BizUser extends BaseModel {
	/** 用户名 */
	private String name;
	/** 账号 */
	private String account;
	/** 密码 */
	private String password;
	/** 手机号 */
	private String mobile;
	/** 用户状态：0-认证中，1-正常用户，2-冻结 */
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "BizUser [name=" + name + ", account=" + account + ", password=" + password + ", mobile=" + mobile
				+ ", status=" + status + ", createBy=" + super.getCreateBy() + ", createTime=" + super.getCreateTime()
				+ ", updateBy=" + super.getUpdateBy() + ", updateTime=" + super.getUpdateTime() + ", versionNo="
				+ super.getVersionNo() + ", enable=" + super.getEnable() + ", remark=" + super.getRemark()
				+ ", orderBy=" + super.getOrderBy() + ", sortAsc=" + super.getSortAsc() + "]";
	}

}
