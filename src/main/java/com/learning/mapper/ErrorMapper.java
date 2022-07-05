package com.learning.mapper;

import java.util.Date;

public class ErrorMapper {
	private String errorMessage;
	private int errorCode;
	private Date timeStamp;
	
	public ErrorMapper() {
		super();
	}
	public ErrorMapper(String errorMessage, int errorCode, Date timeStamp) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.timeStamp = timeStamp;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
