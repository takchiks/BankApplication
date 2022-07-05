package com.learning.pojo;

import com.learning.enums.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CustomerRequest {

    private int customerId;
    @Enumerated(EnumType.STRING)
    private Status status;

    public CustomerRequest(int customerId, Status status) {
        this.customerId = customerId;
        this.status = status;
    }

    public CustomerRequest() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomerRequest{" +
                "customerId=" + customerId +
                ", status=" + status +
                '}';
    }
}
