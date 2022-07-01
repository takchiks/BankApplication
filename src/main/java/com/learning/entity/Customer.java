package com.learning.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Customer extends Person {

	private long phoneNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name ="cus_Acc_tbl", joinColumns = @JoinColumn(name= "person_id"))
	private List<Account> account;

	
	
	public Customer(int personId, String fullName, String userName, String passWord, long phoneNumber) {
		super(personId, fullName, userName, passWord);
		this.phoneNumber = phoneNumber;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(account, phoneNumber);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(account, other.account) && phoneNumber == other.phoneNumber;
	}
	
	
	
	
}
