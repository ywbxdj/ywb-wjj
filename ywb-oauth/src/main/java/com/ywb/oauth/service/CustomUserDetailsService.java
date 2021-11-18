package com.ywb.oauth.service;

import com.ywb.oauth.constants.Auth2Constant;
import com.ywb.oauth.model.CustomUser;
import com.zeiet.auth.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String grant_type = request.getParameter("grant_type");
        CustomUser customUser;
        if (Auth2Constant.REFRESH_TOKEN.equals(grant_type)) {
            //如果是刷新token
            return new User("admin", "password", new ArrayList<>());
        } else {
            customUser = userService.getCustomUserByUserName(userName);
            return customUser;
        }
    }
}
