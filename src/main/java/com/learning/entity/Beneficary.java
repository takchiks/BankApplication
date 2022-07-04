package com.learning.entity;

import com.learning.enums.AccountType;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Beneficary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int benId;
	private long accountNumber;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	private String isApproved;
	private Date date;
	
	public Beneficary() {
		super();
		
	}

	public Beneficary(long accountNumber, AccountType accountType, String isApproved, Date date) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.isApproved = isApproved;
		this.date = date;
	}

	public int getBenId() {
		return benId;
	}

	public void setBenId(int benId) {
		this.benId = benId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
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
