package com.ywb.oauth.model.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonRole implements Serializable {
    private static final long serialVersionUID = 4179038100849351087L;
    private String id;


    /**
     * 角色代码
     */
    private String code;

    /**
     * 上级角色
     */
    private String pcode;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色类型
     */
    private Integer type;


    /**
     * 是否管理员
     */
    private Integer isAdmin;

    /**
     * 数据范围
     */
    private String dataScope;

    /**
     * 备注
     */
    private String remark;

    /**
     * oauthCode
     */
    private String oauthCode;

}
