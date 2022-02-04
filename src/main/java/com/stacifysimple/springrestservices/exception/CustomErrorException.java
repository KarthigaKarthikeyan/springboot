package com.stacifysimple.springrestservices.exception;

import java.util.Date;

//Simple custom error bean 
public class CustomErrorException {

	private Date timestamp;
	private String message;
	private String errorMsg;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public CustomErrorException(Date timestamp, String message, String errorMsg) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorMsg = errorMsg;
	}
	
}
