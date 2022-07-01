package com.learning.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Beneficary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int benId;
	private long accountNumber;
	private String accountType;
	private String isApproved;
	private Date date;
	
	public Beneficary() {
		super();
		
	}

	public Beneficary(long accountNumber, String accountType, String isApproved, Date date) {
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
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
