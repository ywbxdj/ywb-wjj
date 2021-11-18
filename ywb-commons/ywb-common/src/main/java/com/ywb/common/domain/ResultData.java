package com.ywb.common.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zeiet.common.exception.BusinessException;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 结果数据类
 */
@Data
public class ResultData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码", required = true)
    private int code;

    @ApiModelProperty(value = "消息内容", required = true)
    private String msg;

    @ApiModelProperty(value = "时间戳", required = true)
    private long time;

    @ApiModelProperty(value = "业务数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private ResultData() {
        this.time = System.currentTimeMillis();
    }

    private ResultData(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMsg());
    }

    private ResultData(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private ResultData(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMsg());
    }

    private ResultData(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private ResultData(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.time = System.currentTimeMillis();
    }


    /**
     * 返回状态码
     *
     * @param resultCode 状态码
     * @param <T>        泛型标识
     * @return ApiResult
     */
    public static <T> ResultData<T> success(IResultCode resultCode) {
        return new ResultData<>(resultCode);
    }

    public static <T> ResultData<T> success(String msg) {
        return new ResultData<>(ResultCode.SUCCESS, msg);
    }
    public static <T> ResultData<T> success() {
        return new ResultData<>(ResultCode.SUCCESS);
    }
    public static <T> ResultData<T> success(IResultCode resultCode, String msg) {
        return new ResultData<>(resultCode, msg);
    }

    public static <T> ResultData<T> data(T data) {
        return data(data, "处理成功");
    }

    public static <T> ResultData<T> data(T data, String msg) {
        return data(ResultCode.SUCCESS.code, data, msg);
    }

    public static <T> ResultData<T> data(String msg, T data) {
        return data(ResultCode.SUCCESS.code, msg, data);
    }

    public static <T> ResultData<T> data(int code, T data, String msg) {
        return new ResultData<>(code, data, data == null ? "处理失败" : msg);
    }

    public static <T> ResultData<T> data(int code, String msg, T data) {
        return new ResultData<>(code, data, data == null ? "处理失败" : msg);
    }

    public static <T> ResultData<T> error() {
        return new ResultData<>(ResultCode.FAILURE, ResultCode.FAILURE.getMsg());
    }

    public static <T> ResultData<T> error(String msg) {
        return new ResultData<>(ResultCode.FAILURE, msg);
    }

    public static <T> ResultData<T> error(int code, String msg) {
        return new ResultData<>(code, null, msg);
    }

    public static <T> ResultData<T> error(IResultCode resultCode) {
        return new ResultData<>(resultCode);
    }

    public static <T> ResultData<T> error(IResultCode resultCode, String msg) {
        return new ResultData<>(resultCode, msg);
    }

    public static <T> ResultData<T> condition(boolean flag) {
        return flag ? success("处理成功") : error("处理失败");
    }
    
	public T unwrap() {
		if (ResultCode.FAILURE.getCode() == this.getCode()) {
			throw new BusinessException(this.getMsg());
		} else {
			return this.getData();
		}
	}
    
}
