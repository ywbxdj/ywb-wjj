package com.ywb.common.util.valid;

import java.util.List;

public interface ValidShowSupport<R> {

	/**
	 * 批量校验结果集处理
	 * @param errorRowMsgList
	 * @return
	 */
	R getRowResult(List<ErrorRowMsg> errorRowMsgList);
	/**
	 * 单个对象校验结果集处理
	 * @param errorMsgList
	 * @return
	 */
	R getResult(List<ErrorMsg> errorMsgList);
}
