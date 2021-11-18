package com.ywb.common.listener;

import java.util.Date;

public interface IBaseEntityCreate {
    String getId();
    void setId(String id);
    String getCreaterId();
    void setCreaterId(String createrId);
    Date getCreateTime();
    void setCreateTime(Date createTime);
}
