package com.ywb.common.listener.impl;

import com.alibaba.fastjson.annotation.JSONField;
import com.zeiet.common.listener.IBaseEntityUpdate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseEntityUpdate extends BaseEntityCreate implements IBaseEntityUpdate, Serializable {
    @Column(name = "creater_com_id", length = 32,
            columnDefinition = "varchar(32) COMMENT '更新人ID'"
    )
    @JSONField(serialize = false)
    protected String createrComId;

    @Column(name = "updater_id",length = 32,
            columnDefinition = "varchar(32) COMMENT '更新人ID'"
    )
    @JSONField(serialize = false)
    @LastModifiedBy
    protected String updaterId;

    @Column(name = "update_time",
            columnDefinition = "datetime COMMENT '更新时间'"
    )
    @JSONField(serialize = false)
    @LastModifiedDate
    protected Date updateTime;

    @Version
    @Column(name = "version",
            columnDefinition = "int DEFAULT 0 COMMENT '版本'"
    )
    @JSONField(serialize = false)
    protected Integer version;

    public String getUpdaterId() {
        return this.updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        if (version != null && version < 0) {
            version = 0;
        }
        this.version = version;
    }

    public String getCreaterComId() {
        return this.createrComId;
    }

    public void setCreaterComId(String createrComId) {
        this.createrComId = createrComId;
    }
}