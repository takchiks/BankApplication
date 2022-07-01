package com.learning.entity;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
	
	public Admin() {
		super();
		
	}

	public Admin(int personId, String fullName, String userName, String passWord) {
		super(personId, fullName, userName, passWord);
		
	}
	
}
