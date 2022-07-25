package com.learning.pojo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.learning.enums.Status;

public class CustomerRequestUpdate {

	private int userId;
	private String fullName;
	private String userName; 

    @Enumerated(EnumType.STRING)
	private Status status;
	private long phoneNumber;
	private String secret_question;
	private String secret_answer;
	public CustomerRequestUpdate(int userId, String fullName, String userName, Status status, long phoneNumber,
			String secret_question, String secret_answer) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.secret_question = secret_question;
		this.secret_answer = secret_answer;
	}
	public CustomerRequestUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomerRequestUpdate [userId=" + userId + ", fullName=" + fullName + ", userName=" + userName
				+ ", status=" + status + ", phoneNumber=" + phoneNumber + ", secret_question=" + secret_question
				+ ", secret_answer=" + secret_answer + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSecret_question() {
		return secret_question;
	}
	public void setSecret_question(String secret_question) {
		this.secret_question = secret_question;
	}
	public String getSecret_answer() {
		return secret_answer;
	}
	public void setSecret_answer(String secret_answer) {
		this.secret_answer = secret_answer;
	}
	
	

}
