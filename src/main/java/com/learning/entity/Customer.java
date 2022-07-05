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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cus_Acc_tbl", joinColumns = @JoinColumn(name = "person_id"))
    private List<Account> account;


    public Customer(int personId, String fullName, String userName, String passWord, long phoneNumber, Status status) {
        super(personId, fullName, userName, passWord);
        this.phoneNumber = phoneNumber;
        this.status = status;
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
