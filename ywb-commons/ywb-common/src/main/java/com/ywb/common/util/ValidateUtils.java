package com.ywb.common.util;

import com.google.common.collect.Lists;
import com.zeiet.common.util.valid.ErrorMsg;
import com.zeiet.common.util.valid.ErrorRowMsg;
import com.zeiet.common.util.valid.ValidShowSupport;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
public class ValidateUtils<T,R> {

	private Validator validator;
	
	//多行记录错误信息
	private List<ErrorRowMsg> errorRowMsgList = Lists.newArrayList();
	
	//单行记录错误信息
	private List<ErrorMsg> errorMsgList = Lists.newArrayList();
	
	private Integer rowIndex = 2;
	
	public ValidateUtils(@NotBlank Validator validator) {
		this.validator = validator;
	}
	
	/**
	 * 多行校验
	 * @param list
	 * @return
	 */
	public ValidateUtils<T,R> validRowList (List<T> list) {
		for (T row : list) {
			//按行遍历
			Set<ConstraintViolation<T>> validSet = validator.validate(row);
			if (CollectionUtils.isNotEmpty(validSet)) {
				List<ErrorMsg> errorMsgList = Lists.newArrayList();
				for (ConstraintViolation<T> data : validSet) {
					errorMsgList.add(getErrorMsg(data));
				}
				
				ErrorRowMsg errorRowMsg = new ErrorRowMsg();
				errorRowMsg.setErrorMsgList(errorMsgList);
				errorRowMsg.setRowIndex(rowIndex);
				errorRowMsgList.add(errorRowMsg);
			}
			rowIndex++;
		}
		return this;
	}
	
	/**
	 * 单行校验
	 * @param t
	 * @return
	 */
	public ValidateUtils<T,R> validData (T t) {
		Set<ConstraintViolation<T>> validSet = validator.validate(t);
		if (CollectionUtils.isNotEmpty(validSet)) {
			for (ConstraintViolation<T> data : validSet) {
				ErrorMsg errorRowMsg = getErrorMsg(data);
				errorMsgList.add(errorRowMsg);
			}
		}
		return this;
	}
	
	public R errorMsg (ValidShowSupport<R> validShowSupport) {
		if (CollectionUtils.isNotEmpty(errorRowMsgList)) {
			return validShowSupport.getRowResult(errorRowMsgList);
		}
		if (CollectionUtils.isNotEmpty(errorMsgList)) {
			return validShowSupport.getResult(errorMsgList);
		}
		return null;
	}
	
	private ErrorMsg getErrorMsg (ConstraintViolation<T> data) {
		ErrorMsg errorMsg = new ErrorMsg();
		errorMsg.setMessage(data.getMessage());
		errorMsg.setFiledName(data.getPropertyPath().toString());
		return errorMsg;
	}
	
}
