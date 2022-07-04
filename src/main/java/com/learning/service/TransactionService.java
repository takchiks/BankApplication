package com.learning.service;

import java.util.List;

import com.learning.entity.Account;
import com.learning.entity.Transaction;

public interface TransactionService {

	public Transaction addTransaction(Transaction transaction);
	public List<Transaction> getAllTransaction();
	public Transaction getAccountById(int transactionId);
	public Transaction updateAccount(Transaction tsransaction);
	
}
