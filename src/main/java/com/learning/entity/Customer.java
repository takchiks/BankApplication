package com.learning.entity;

import com.learning.enums.Status;

import javax.persistence.*;
import java.util.List;

import java.util.Objects;

@Entity
public class Customer extends User {

    @Enumerated(EnumType.STRING)
    private Status status;

    private long phoneNumber;
    private String secret_question;
    private String secret_answer;

	

	public String getSecret_question() {
		return secret_question;
	}



	public void setSecret_question(String secret_question) {
		this.secret_question = secret_question;
	}



	public String getSecret_answer() {
		return secret_answer;
	}



	public void setSecret_answer(String secret_answer) {
		this.secret_answer = secret_answer;
	}



	public long getPhoneNumber() {
		return phoneNumber;
	}

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cus_Acc_tbl", joinColumns = @JoinColumn(name = "person_id"))
    private List<Account> account;



    public Customer(int personId, String fullName, String userName, String passWord, long phoneNumber, Status status) {
        super(personId, fullName, userName, passWord);
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    

    public Customer(int userId, String fullName, String userName, String passWord, Status status, long phoneNumber,
			String secret_question, String secret_answer) {
		super(userId, fullName, userName, passWord);
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.secret_question = secret_question;
		this.secret_answer = secret_answer;
		
	}
    
    



	@Override
	public String toString() {
		return "Customer [status=" + status + ", phoneNumber=" + phoneNumber + ", secret_question=" + secret_question
				+ ", secret_answer=" + secret_answer + ", account=" + account + "]";
	}



	public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
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

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addAccount(Account acc) {
		account.add(acc);
		
	}
	
    public List<Account> getAccount() {
        return account;
    }


    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(account, phoneNumber);
        return result;
    }

    


}
