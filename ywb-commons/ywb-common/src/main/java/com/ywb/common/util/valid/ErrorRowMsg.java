package com.ywb.common.util.valid;

import lombok.Data;

import java.util.List;

@Data
public class ErrorRowMsg {
	private Integer rowIndex;
	
	private List<ErrorMsg> errorMsgList;
}
