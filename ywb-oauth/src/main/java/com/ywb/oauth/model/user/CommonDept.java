package com.ywb.oauth.model.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonDept implements Serializable {
    private static final long serialVersionUID = 6263344799560131802L;
    private String id;
    /**
     * 排序
     */
    private Integer num;
    /**
     * 父部门id
     */
    private String pid;
    /**
     * 父级ids
     */
    private String code;
    /**
     * 简称
     */
    private String simplename;
    /**
     * 全称
     */
    private String fullname;
    /**
     * 提示
     */
    private String tips;

    /**
     * 所属公司id
     */
    private String companyId;

    /**
     * 是否叶子节点
     */
    private Boolean isLeaf;

    /**
     * 是否叶子节点
     */
    private String status;

    private Integer type;

    /**
     * 用户登录选择一个部门
     */
    private boolean checked = false;
}
