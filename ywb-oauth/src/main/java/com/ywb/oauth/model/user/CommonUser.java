package com.ywb.oauth.model.user;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;

@Data
public class CommonUser implements Serializable {

    private static final long serialVersionUID = 4414143280506885969L;
    private String id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String password;
    /**
     * md5密码盐
     */
    private String salt;
    /**
     * 名字
     */
    private String name;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 性别（1：男 2：女）
     */
    private String sex;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态(1：启用  0：冻结）
     */
    private String status;

    private String companyName;

    private String companyId;

    private String wxUserName;

    /**
     * PID值
     */
    @Transient
    private String secretPid;
    /**
     * 秘钥
     */
    @Transient
    private String secretKey;
    /**
     * 秘钥状态
     */
    @Transient
    private String secretStatus;

}
