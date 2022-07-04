package com.learning.entity;

import com.learning.enums.AccountType;
import com.learning.enums.Status;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Account {
    @Id
	private int accountNumber;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	private double accountBalance;
	private boolean isApproved;
	private Date dateOfCreation;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany
	@JoinTable(name = "acc_Tra_tbl",joinColumns =@JoinColumn(name ="accountNumber"))
	private List<Transaction> transaction;
	
	@OneToMany
	@JoinTable(name = "acc_Ben_tbl",joinColumns =@JoinColumn(name ="accountNumber"))
	private List<Beneficary> beneficary;

	public Account(AccountType accountType, double accountBalance, boolean isApproved, Date dateOfCreation, Status status) {
		super();
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.isApproved = isApproved;
		this.dateOfCreation = dateOfCreation;
		this.status = status;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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
