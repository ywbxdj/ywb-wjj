package com.ywb.common.listener.impl;

import com.zeiet.common.listener.IBaseEntityCreate;
import com.zeiet.common.model.BaseModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseEntityCreate extends BaseModel implements IBaseEntityCreate, Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "creater_id",length = 32,updatable = false,
            columnDefinition = "varchar(32) COMMENT '创建人ID'"
    )
    @CreatedBy
    protected String createrId;

    @Column(name = "create_time",updatable = false,
            columnDefinition = "datetime COMMENT '创建时间'"
    )
    @CreatedDate
    protected Date createTime;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreaterId() {
        return this.createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
