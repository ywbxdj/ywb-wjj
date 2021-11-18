package com.ywb.oauth.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ExtendPasswordEncoder extends BCryptPasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        String password = rawPassword.toString();
        if (password.startsWith("encode:")) {
            return rawPassword.toString();
        } else {
            return super.encode(rawPassword);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String password = rawPassword.toString();
        if (password.startsWith("encode:")) {
            password = password.substring(7);
            return encodedPassword.equals(password);
        } else {
            return super.matches(rawPassword, encodedPassword);
        }
    }
}
