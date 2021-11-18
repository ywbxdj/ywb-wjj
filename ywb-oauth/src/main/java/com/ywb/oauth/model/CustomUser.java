package com.ywb.oauth.model;

import com.ywb.oauth.model.user.CommonUser;
import com.ywb.oauth.model.user.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 */
@Getter
@Setter
public class CustomUser extends User implements Serializable{
    private static final long serialVersionUID = 1L;
    private UserInfo userInfo;

    public CustomUser(CommonUser commonUser, UserInfo userInfo, List<GrantedAuthority> authorities) {
        super(commonUser.getAccount(), commonUser.getPassword(), authorities);
        this.userInfo = userInfo;
    }
}