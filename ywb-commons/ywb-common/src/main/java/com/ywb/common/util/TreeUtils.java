package com.ywb.common.util;

import com.ywb.common.api.TreeOper;
import com.ywb.common.constant.FieldConstant;
import com.ywb.common.exception.BusinessException;
import com.ywb.common.vo.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeUtils {
    public static <T> List<TreeNode<T>> getTree(List<T> list, TreeOper<T> operator) {
        List<T> topList = list.stream().filter(o -> FieldConstant.ROOT.equals(operator.getParent(o))).collect(Collectors.toList());
        list.removeAll(topList);
        List<TreeNode<T>> parents = new ArrayList<>();
        setChildren(topList, list, parents, operator);
        if (list.size() > 0)  {
            list.forEach(o -> {
                List<T> tops = new ArrayList<>();
                tops.add(o);
                loopParent(o, tops, parents, operator);
            });
        }
        return parents;
    }

    private static <T> void loopParent(T it, List<T> tops, List<TreeNode<T>> ret, TreeOper<T> operator) {
        List<T> parents = operator.getParentList(it);
        if (parents.size() > 0) {
            T parent = parents.get(0);
            long count = tops.stream().filter(o -> operator.getId(o).equals(operator.getId(parent))).count();
            if (count > 0) {
                throw new BusinessException("数据有误，无限递归循环！");
            }
            tops.add(parent);
            if (!"0".equals(operator.getParent(parent))) {
                loopParent(parent, tops, ret, operator);
            } else {
                setChildren(parents, tops, ret, operator);
            }
        }
    }
 
    private static <T> void setChildren(List<T> topList, List<T> allList,  List<TreeNode<T>> treeNode, TreeOper<T> operator) {
        topList.forEach(o -> {
            TreeNode<T> child = new TreeNode();
            child.setId(operator.getId(o));
            child.setPid(operator.getParent(o));
            child.setName(operator.getName(o));
            child.setData(o);
            treeNode.add(child);
            List<T> children = allList.stream().filter(c -> operator.getIem(o).equals(operator.getParent(c))).collect(Collectors.toList());
            if (children.size() > 0) {
                allList.removeAll(children);
                child.newChildren();
            }
            setChildren(children, allList, child.getChildren(), operator);
        });
    }
}
