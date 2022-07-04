package com.learning.repo;

import com.learning.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
    public List<Account> findByIsApproved(boolean isApproved);
}
