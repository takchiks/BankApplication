package com.learning.entity;

import javax.persistence.Entity;
import javax.xml.crypto.Data;

@Entity
public class Transaction {

	private Data date;
	private String reference;
	private double amount;
	private int toAcc;
	private int fromAcc;
	private String reason;
	private PaymenType paymentType;
	
	public enum PaymenType{	
		DEBIT , CREDIT
	}

	public Transaction(Data date, String reference, double amount, int toAcc, int fromAcc, String reason,
			PaymenType paymentType) {
		super();
		this.date = date;
		this.reference = reference;
		this.amount = amount;
		this.toAcc = toAcc;
		this.fromAcc = fromAcc;
		this.reason = reason;
		this.paymentType = paymentType;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Data getDate() {
		return date;
	}

	public void setDate(Data date) {
		this.date = date;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getToAcc() {
		return toAcc;
	}

	public void setToAcc(int toAcc) {
		this.toAcc = toAcc;
	}

	public int getFromAcc() {
		return fromAcc;
	}

	public void setFromAcc(int fromAcc) {
		this.fromAcc = fromAcc;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public PaymenType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymenType paymentType) {
		this.paymentType = paymentType;
	}
	
	
	
	
}
