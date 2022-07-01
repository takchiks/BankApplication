package com.learning.repo;

import com.learning.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account,Long> {
    public List<Account> findByIsApproved(boolean isApproved);
}
