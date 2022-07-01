package com.learning.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String fullName;
	private String userName;
	private String passWord;
	public User(int userId, String fullName, String userName, String passWord) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getuserId() {
		return userId;
	}
	public void setPersonId(int personId) {
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
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@Override
	public int hashCode() {
		return Objects.hash(fullName, passWord, userId, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(fullName, other.fullName) && Objects.equals(passWord, other.passWord)
				&& userId == other.userId && Objects.equals(userName, other.userName);
	}
	
	
	
	
	
}
