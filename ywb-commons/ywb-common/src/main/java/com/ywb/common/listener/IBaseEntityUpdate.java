package com.ywb.common.listener;

import java.util.Date;

public interface IBaseEntityUpdate extends IBaseEntityCreate {
    String getUpdaterId();

    void setUpdaterId(String updaterId);

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);

    Integer getVersion();

    void setVersion(Integer version);

    String getCreaterComId();

    void setCreaterComId(String createrComId);
}
