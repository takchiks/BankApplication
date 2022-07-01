package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learning.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
