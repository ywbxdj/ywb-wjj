package com.ywb.oauth.model.user;

import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;


@Data
public class CommonModuleForm implements Serializable {
    private static final long serialVersionUID = 2458742039986328707L;

    private String id;
    /**
     * 查询sql
     */
    private String selectSql;

    /**
     * 查询参数
     */
    private String queryParams;

    /**
     * 更新Sql
     */
    private String updateSql;
    /**
     * 删除sql
     */
    private String deleteSql;

    /**
     * 新增sql
     */
    private String insertSql;
    /**
     * 表单描述
     */
    private String formDesc;

    /**
     * 数据库表名
     */
    private String tableName;
    /**
     * 模块描述名称
     */
    private String moduleName;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 流程定义标识
     */
    private String procDefName;

    /**
     * 表单设计json
     */
    private String formJson;

    /**
     * 自定义表描述
     */
    private String tableDesc;

    @Transient
    private String menuName;

    @Transient
    private String menuCode;

    @Transient
    private String path;

    @Transient
    private String pcode;

    @Transient
    //show or hidden 取决menu 表hidden字段
    private String menuStatus;
}
