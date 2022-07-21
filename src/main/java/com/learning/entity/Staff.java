package com.learning.entity;

import com.learning.enums.Status;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Staff extends User {

    @Enumerated(EnumType.STRING)
	private Status status;

	public Staff() {
		super();
		
	}

	public Staff(int userId, String fullName, String userName, String passWord) {
		super(userId, fullName, userName, passWord);
		
	}


	public Staff(int userId, String fullName, String userName, String passWord, Status status) {
		super(userId, fullName, userName, passWord);
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	

}
