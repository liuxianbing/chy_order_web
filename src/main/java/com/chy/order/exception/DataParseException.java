package com.chy.order.exception;

import com.chy.order.util.HttpCode;

/**
 * @author liuxianbing
 * @version 2017年5月20日 下午3:19:19
 */
@SuppressWarnings("serial")
public class DataParseException extends BaseException {

	public DataParseException() {
	}

	public DataParseException(Throwable ex) {
		super(ex);
	}

	public DataParseException(String message) {
		super(message);
	}

	public DataParseException(String message, Throwable ex) {
		super(message, ex);
	}

	protected HttpCode getHttpCode() {
		return HttpCode.INTERNAL_SERVER_ERROR;
	}

}
