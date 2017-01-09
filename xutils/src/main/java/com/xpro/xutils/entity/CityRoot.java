package com.xpro.xutils.entity;


public class CityRoot {
	private String success;

	private CityBody body;

	private String errorCode;

	private String msg;

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getSuccess() {
		return this.success;
	}

	public void setBody(CityBody body) {
		this.body = body;
	}

	public CityBody getBody() {
		return this.body;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}
}
