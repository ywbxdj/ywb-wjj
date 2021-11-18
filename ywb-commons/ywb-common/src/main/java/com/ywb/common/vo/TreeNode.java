package com.ywb.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNode<T> implements Serializable {
    private String id;    //节点id

    private String pid;//父节点id

    private String name;//节点名称

    private List<TreeNode<T>> children;

    private T data;


    public List<TreeNode<T>> newChildren() {
        if (null == children) {
            children = new ArrayList<>();
        }
        return children;
    }
}
