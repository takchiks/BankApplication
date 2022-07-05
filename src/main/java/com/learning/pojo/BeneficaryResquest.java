package com.learning.pojo;

import java.util.Date;

public class BeneficaryResquest {
    private int fromCustomer;
    private int beneficiaryAcNo;
    private Date beneficiaryAddedDate;
    private String approved;

    public BeneficaryResquest(int fromCustomer, int beneficiaryAcNo, Date beneficiaryAddedDate, String approved) {
        this.fromCustomer = fromCustomer;
        this.beneficiaryAcNo = beneficiaryAcNo;
        this.beneficiaryAddedDate = beneficiaryAddedDate;
        this.approved = approved;
    }

    public BeneficaryResquest() {
    }

    public int getFromCustomer() {
        return fromCustomer;
    }

    public void setFromCustomer(int fromCustomer) {
        this.fromCustomer = fromCustomer;
    }

    public int getBeneficiaryAcNo() {
        return beneficiaryAcNo;
    }

    public void setBeneficiaryAcNo(int beneficiaryAcNo) {
        this.beneficiaryAcNo = beneficiaryAcNo;
    }

    public Date getBeneficiaryAddedDate() {
        return beneficiaryAddedDate;
    }

    public void setBeneficiaryAddedDate(Date beneficiaryAddedDate) {
        this.beneficiaryAddedDate = beneficiaryAddedDate;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "BeneficaryResquest{" +
                "fromCustomer=" + fromCustomer +
                ", beneficiaryAcNo=" + beneficiaryAcNo +
                ", beneficiaryAddedDate=" + beneficiaryAddedDate +
                ", approved='" + approved + '\'' +
                '}';
    }
}
