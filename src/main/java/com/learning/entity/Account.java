package com.learning.entity;

<<<<<<< HEAD
import com.learning.enums.AccountTyoe;

=======
import com.learning.enums.AccountType;
>>>>>>> branch 'master' of https://github.com/takchiks/BankApplication
import com.learning.enums.Status;

import java.util.Date;
import java.util.List;

<<<<<<< HEAD
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
=======
import javax.persistence.*;
>>>>>>> branch 'master' of https://github.com/takchiks/BankApplication

@Entity
public class Account {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long accountNumber;
	private AccountTyoe accountType;
=======
	private int accountNumber;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
>>>>>>> branch 'master' of https://github.com/takchiks/BankApplication
	private double accountBalance;
	private boolean isApproved;
	private Date dateOfCreation;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany
	@JoinTable(name = "acc_Tra_tbl",joinColumns =@JoinColumn(name ="accountNumber"))
	private List<Transaction> transaction;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "acc_Ben_tbl",joinColumns =@JoinColumn(name ="accountNumber"))
	private List<Beneficary> beneficary;

<<<<<<< HEAD
	public Account(AccountTyoe accountType, double accountBalance, boolean isApproved, Status status) {
=======
	public Account(AccountType accountType, double accountBalance, boolean isApproved, Date dateOfCreation, Status status) {
>>>>>>> branch 'master' of https://github.com/takchiks/BankApplication
		super();
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.isApproved = isApproved;
		this.dateOfCreation = new Date();
		this.status=status;
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
		this.dateOfCreation = new Date();
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
	
	public void addbeneficiary(Beneficary ben) {
		 beneficary.add(ben);
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void remove(Beneficary ben) {
		beneficary.remove(ben);
	}
	public void addtransaction(Transaction trans) {
		transaction.add(trans);
	}
	
}
