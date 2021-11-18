package com.ywb.common.util.valid;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 默认的校验器结果集处理类
 * @author lusy
 *
 * @param <T>
 */
public class DefaultValidShowSupport implements ValidShowSupport<String> {

	private static final String ROW_ERROR_MSG_CONTENT = "第%s行数据:%s";
	
	@Override
	public String getRowResult(List<ErrorRowMsg> errorRowMsgList) {
		if (CollectionUtils.isEmpty(errorRowMsgList)) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		errorRowMsgList.forEach(data -> {
			sb.append(",")
			.append(String.format(ROW_ERROR_MSG_CONTENT, data.getRowIndex(), getResult(data.getErrorMsgList())));
		});
		return sb.substring(1);
	}
	
	@Override
	public String getResult(List<ErrorMsg> errorMsgList) {
		if (CollectionUtils.isEmpty(errorMsgList)) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		errorMsgList.forEach(data -> {
			sb.append(",").append(data.getMessage());
		});
		return sb.substring(1);
	}
}
