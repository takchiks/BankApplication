package com.learning.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
	private long accountNumber;
	private String accountType;
	private double accountBalance;
	private boolean isApproved;
	private Date dateOfCreation;
	private String status;
	
	@OneToMany
	@JoinTable(name = "acc_Tra_tbl",joinColumns =@JoinColumn(name ="accountNumber"))
	private List<Transaction> transaction;
	
	@OneToMany
	@JoinTable(name = "acc_Ben_tbl",joinColumns =@JoinColumn(name ="accountNumber"))
	private List<Beneficary> beneficary;

	public Account(String accountType, double accountBalance, boolean isApproved, Date dateOfCreation, String status) {
		super();
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.isApproved = isApproved;
		this.dateOfCreation = dateOfCreation;
		this.status = status;
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

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public List<Beneficary> getBeneficary() {
		return beneficary;
	}

	public void setBeneficary(List<Beneficary> beneficary) {
		this.beneficary = beneficary;
	}
	
	
	
	
}
