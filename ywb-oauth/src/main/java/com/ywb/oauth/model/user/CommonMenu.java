package com.ywb.oauth.model.user;

import lombok.Data;

import java.io.Serializable;


@Data
public class CommonMenu implements Serializable {
    private static final long serialVersionUID = -4502044877180618313L;
    private String id;
    /**
     * 所属子系统编号
     */
    private String sysCode;
    /**
     * 菜单编号
     */
    private String code;
    /**
     * 菜单父编号
     */
    private String pcode;
    /**
     * 类型
     */
    private String type;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * url地址
     */
    private String path;
    /**
     * 菜单排序号
     */
    private Integer num;
    /**
     * 菜单层级
     */
    private Boolean hidden;

    /**
     * 是否是菜单（1：是  0：不是）
     */
    private Boolean isLeaf;

    /**
     * 是否是外链（1：是  0：不是）
     */
    private Boolean iframe;

    /**
     * 菜单缓存（1：是  0：不是）
     */
    private Boolean cache;

    /**
     * 备注
     */
    private String remark;

    /**
     * 组件路径
     */
    private String component;
}
