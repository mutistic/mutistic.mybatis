package com.mutistic.mybatis.java.one.dto;

import java.io.Serializable;

import com.mutistic.mybatis.java.model.BizAddress;
import com.mutistic.mybatis.java.model.BizUser;

/**
 * @program 一对一关系映射DTO对象
 * @description
 * @author mutisitic
 * @date 2019年1月21日
 */
@SuppressWarnings("serial")
public class OneToOneDto implements Serializable {
	/** BizUser对象 */
	private BizUser bizUser;
	/** BizAddress对象 */
	private BizAddress bizAddress;

	public BizAddress getBizAddress() {
		return bizAddress;
	}
	public void setBizAddress(BizAddress bizAddress) {
		this.bizAddress = bizAddress;
	}
	public BizUser getBizUser() {
		return bizUser;
	}
	public void setBizUser(BizUser bizUser) {
		this.bizUser = bizUser;
	}
	@Override
	public String toString() {
		return "OneToOneDto [bizUser=" + bizUser + ", bizAddress=" + bizAddress + "]";
	}
}
