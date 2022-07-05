package com.learning.service;

import java.util.List;

import com.learning.entity.Account;
import com.learning.entity.Transaction;

public interface TransactionService {

	public Transaction addTransaction(Transaction transaction);
	public List<Transaction> getAllTransaction();
	public Transaction getTransactionById(int transactionId);
	public Transaction updateTransaction(Transaction tsransaction);
	
}
