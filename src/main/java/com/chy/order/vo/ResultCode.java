package com.chy.order.vo;
/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月6日 下午6:27:05 
* 类说明 
*/
public enum ResultCode {

	SUCCEED("1", "成功"),
	ERROR("0", "失败");
	
	final String code;
	final String msg;

	private ResultCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
