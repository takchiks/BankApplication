package com.learning.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.enums.RoleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String fullName;
	@Column(unique=true)
	private String userName;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String passWord;

	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	public User(int userId, String fullName, String userName, String passWord) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	
	public User(String fullName, String userName, String passWord, RoleType role) {
		super();
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}


	public User(int userId, String fullName, String userName, String passWord, RoleType role) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}


	public User() {
		super();
	}
	public int getUserId() {
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



	public void setUserId(int userId) {
		this.userId = userId;
	}


	public RoleType getRole() {
		return role;
	}


	public void setRole(RoleType role) {
		this.role = role;
	}


	public boolean getIsActive() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	
	
}
