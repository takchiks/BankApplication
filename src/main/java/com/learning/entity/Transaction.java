package com.learning.entity;

import com.learning.enums.PaymentType;

import javax.persistence.*;

import org.hibernate.annotations.GeneratorType;

import java.util.Date;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;

	private Date date;
	private String reference;
	private double amount;
	private int toAcc;
	private int fromAcc;
	private String reason;
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	


	public Transaction(Date date, String reference, double amount, int toAcc, int fromAcc, String reason,
			PaymentType paymentType) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"transactionId=" + transactionId +
				", date=" + date +
				", reference='" + reference + '\'' +
				", amount=" + amount +
				", toAcc=" + toAcc +
				", fromAcc=" + fromAcc +
				", reason='" + reason + '\'' +
				", paymentType=" + paymentType +
				'}';
	}
}
