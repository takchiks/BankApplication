package com.learning.pojo;

import com.learning.enums.AccountType;
import org.apache.logging.log4j.message.StringFormattedMessage;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

public class AccountRequest {
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String customerName;
    private int accountNumber;
    private Date dateCreated;
    private String approved;
    private String staffUserName;

    public AccountRequest(AccountType accountType, String customerName, int accountNumber, Date dateCreated, String approved, String staffUserName) {
        this.accountType = accountType;
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.dateCreated = dateCreated;
        this.approved = approved;
        this.staffUserName = staffUserName;
    }

    public AccountRequest() {
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getStaffUserName() {
        return staffUserName;
    }

    public void setStaffUserName(String staffUserName) {
        this.staffUserName = staffUserName;
    }

    @Override
    public String toString() {
        return "AccountRequest{" +
                "accountType=" + accountType +
                ", customerName='" + customerName + '\'' +
                ", accountNumber=" + accountNumber +
                ", dateCreated=" + dateCreated +
                ", approved='" + approved + '\'' +
                ", staffUserName='" + staffUserName + '\'' +
                '}';
    }
}
