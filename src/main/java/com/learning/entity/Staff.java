package com.learning.entity;

import javax.persistence.Entity;

@Entity
public class Staff extends Person {
     
	private String status;

	public Staff() {
		super();
		
	}

	public Staff(int personId, String fullName, String userName, String passWord) {
		super(personId, fullName, userName, passWord);
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
