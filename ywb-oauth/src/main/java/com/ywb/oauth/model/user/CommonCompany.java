package com.ywb.oauth.model.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonCompany implements Serializable {
    private static final long serialVersionUID = -5639709709808883449L;
    private String id;
    //简称
    private String simplename;

    //全称
    private String fullname;

    //公司类型
    private String companyType;

    //区域(字典)
    private String companyRegion;

    //logo
    private String logo;

    //code
    private String code;

    //所属公司oauthcode
    private String oauthCode;
    //所属部门oauthcode
    private String deptOauthCode;

}
