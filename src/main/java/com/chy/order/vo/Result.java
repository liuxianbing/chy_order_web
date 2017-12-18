package com.chy.order.vo;

import java.io.Serializable;

public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String msg;
	private T data;

	public Result(ResultCode code) {
		super();
		this.code = code.getCode();
		this.msg = code.getMsg();
	}

	public Result(ResultCode code, String msg) {
		super();
		this.code = code.getCode();
		this.msg = msg;
	}

	public Result(ResultCode code, T data) {
		super();
		this.code = code.getCode();
		this.data = data;
	}
	
	public void setResultCode(ResultCode code) {
		this.code = code.getCode();
		this.msg = code.getMsg();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}