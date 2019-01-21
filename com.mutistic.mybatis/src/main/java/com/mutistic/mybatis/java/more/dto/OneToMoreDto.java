package com.mutistic.mybatis.java.more.dto;

import java.util.List;

import com.mutistic.mybatis.java.model.BizAddress;
import com.mutistic.mybatis.java.model.BizUser;

/**
 * @program 一对多关系映射DTO对象
 * @description 
 * @author mutisitic
 * @date 2019年1月21日
 */
public class OneToMoreDto {
	/** BizUser对象 */
	private BizUser bizUser;
	/** BizAddress集合 */
	private List<BizAddress> bizAddressList;
	public BizUser getBizUser() {
		return bizUser;
	}
	public void setBizUser(BizUser bizUser) {
		this.bizUser = bizUser;
	}
	public List<BizAddress> getBizAddressList() {
		return bizAddressList;
	}
	public void setBizAddressList(List<BizAddress> bizAddressList) {
		this.bizAddressList = bizAddressList;
	}
	@Override
	public String toString() {
		return "OneToMoreDto [bizUser=" + bizUser + ", bizAddressList=" + bizAddressList + "]";
	}
}
