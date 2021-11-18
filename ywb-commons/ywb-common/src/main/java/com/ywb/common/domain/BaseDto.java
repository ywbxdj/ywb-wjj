package com.ywb.common.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {
    private static final long serialVersionUID = 1L;

    private String id;

    private Integer pageIndex;

    private Integer pageSize;


}
