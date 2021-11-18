package com.ywb.common.api;

import java.util.List;

public interface TreeOper<T> {
    /**
     * 必须， 当前匹配字段
     */
    String getIem(T o);

    /**
     * 必须， 上级匹配字段
     */
    String getParent(T o);

    String getId(T o);

    String getName(T o);

    /**
     * 必须， 用来查询上级信息
     */
    List<T> getParentList(T it);
}
