package com.ywb.oauth.model.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8923597157390482616L;
    private CommonUser sysUser;
    private Set<CommonRole> roles;   // 角色集合
    private Set<String> roleIds;   // 角色id集合
    private Set<CommonDept> depts;//部门集合
    private List<TreeNode<CommonMenu>> menuList;
    //导航栏菜单不含接口级别
    private List<String> authCodes;
    private CommonCompany sysCompany;

    private Boolean isAdmin = null;

    public UserInfo validAdmin() {
        if (isAdmin == null) {
            isAdmin = false;
            for (CommonRole sysRole : roles) {
                if (sysRole.getIsAdmin() != null && sysRole.getIsAdmin() == 1) {
                    isAdmin = true;
                    break;
                }
            }
        }
        return this;
    }
}
