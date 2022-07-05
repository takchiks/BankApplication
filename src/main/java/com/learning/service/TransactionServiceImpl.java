package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Transaction;
import com.learning.repo.TransactionRepo;
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
   private TransactionRepo transactionRepo;
	
	@Override
	public Transaction addTransaction(Transaction transaction) {
		
		return transactionRepo.save(transaction);
	}

	@Override
	public List<Transaction> getAllTransaction() {
		
		return transactionRepo.findAll();
	}

	@Override
	public Transaction getTransactionById(int transactionId) {
		
		return transactionRepo.findById(transactionId).get();
	}

	@Override
	public Transaction updateTransaction(Transaction tsransaction) {
		
		return transactionRepo.save(tsransaction);
	}

}
