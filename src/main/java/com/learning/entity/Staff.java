package com.learning.entity;

import com.learning.enums.Status;

import javax.persistence.Entity;

@Entity
public class Staff extends User {
     
	private Status status;

	public Staff() {
		super();
		
	}

	public Staff(int personId, String fullName, String userName, String passWord) {
		super(personId, fullName, userName, passWord);
		
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	

}
