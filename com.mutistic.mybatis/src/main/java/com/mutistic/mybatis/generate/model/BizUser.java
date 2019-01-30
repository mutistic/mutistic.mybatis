package com.mutistic.mybatis.generate.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户[biz_user]")
@SuppressWarnings("serial")
public class BizUser extends BaseModel {
    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("用户状态：0-认证中，1-正常用户，2-冻结")
    private Integer status;

    /**
     * getter 用户名
     * @return name_ 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * setter 用户名
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter 账号
     * @return account_ 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * setter 账号
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * getter 密码
     * @return password_ 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter 手机号
     * @return mobile_ 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * setter 手机号
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * getter 用户状态：0-认证中，1-正常用户，2-冻结
     * @return status_ 用户状态：0-认证中，1-正常用户，2-冻结
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * setter 用户状态：0-认证中，1-正常用户，2-冻结
     * @param status 用户状态：0-认证中，1-正常用户，2-冻结
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}