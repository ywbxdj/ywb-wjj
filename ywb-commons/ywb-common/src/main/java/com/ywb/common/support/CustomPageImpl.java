package com.ywb.common.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis结果集特殊处理转出jpa-page对象
 * 自定义Jpa-Page实例
 * @date 15/8/24
 */
public class CustomPageImpl <T> extends PageImpl<T> implements Page<T> {
    private static final long serialVersionUID = 1;
    private  List<T> content = new ArrayList<T>();

    public CustomPageImpl(List<T> content) {
        super(content);
        Assert.notNull(content, "Content must not be null!");
        this.content.addAll(content);
    }
    @Override
    public List<T> getContent() {
        return  this.content;
    }
}
