package com.learning.service;

import com.learning.entity.Account;
import com.learning.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepo accountRepo;

    public List<Account> findByIsApproved(boolean isApproved){
        return accountRepo.findByIsApproved(isApproved);
    }
}
