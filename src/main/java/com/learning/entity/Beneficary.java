package com.learning.entity;

import com.learning.enums.AccountType;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Beneficary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int benId;
	@Column(unique=true)
	private int accountNumber;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	private String isApproved;
	private Date date;
	
	public Beneficary() {
		super();
		
	}
	
	public Beneficary(int accountNumber, AccountType accountType, String isApproved) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.isApproved = isApproved;
		this.date = new Date();
	}

	public int getBenId() {
		return benId;
	}

	public void setBenId(int benId) {
		this.benId = benId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String isApproved() {
		return isApproved;
	}

	public void setApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
