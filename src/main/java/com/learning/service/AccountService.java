package com.learning.service;

import java.util.List;

import com.learning.entity.Account;

public interface AccountService {
	
	public Account addAccount(Account account);
	public List<Account> getAllAccount();
	public Account getAccountById(int accountNumber);
	public Account updateAccount(Account account);
	public List<Account> findByIsApproved(boolean isApproved);
}
