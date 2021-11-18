package com.ywb.oauth.model.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonUserRole implements Serializable {
    private static final long serialVersionUID = 2458742039986328707L;
    private String id;
    private String userId;
    private String roleId;
}
