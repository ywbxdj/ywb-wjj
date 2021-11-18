package com.ywb.oauth.model.user;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class CommonModuleFormField implements Serializable {
    private static final long serialVersionUID = 2458742039986328707L;

    private String id;
    /**
     * 数据库列名
     */
    private String columnName;
    /**
     * 字段代码
     */
    private String fieldCode;

    /**
     * 字段描述
     */
    private String fieldDesc;

    /**
     * 字段类型
     */
    private String fieldType;


    /**
     * 关联模块表单id
     */

    private String moduleFormId;
    /**
     * 列表排序
     */
    private Integer listSort;
    /**
     * 表单排序
     */
    private Integer formSort;

    /**
     * 控件字段格式化
     */
    private String vueFormat;

    /**
     * 是否表单显示1是2否
     */
    private Integer formShow;

    /**
     * 是否列表显示1是2否
     */
    private Integer listShow;

    /**
     * 控件类型
     */
    @Column(nullable = false, columnDefinition = "varchar(32) COMMENT '' ")
    private String vueType;
    /**
     * 控件长度(百分比)
     */
    private Integer vueLength;
    /**
     * 控件label显示值
     */
    private String vueLabel;

    /**
     * 是否查询(1是2否)
     */
    private Integer isQuery;
    /**
     * 查询类型
     */
    private String queryType;
}
