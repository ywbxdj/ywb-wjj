package com.ywb.common.domain;

import lombok.Data;

@Data
public class ParamPage {
    private int pageNo = 0;
    private int pageSize = 10;
    private boolean isAdmin = false;
}
