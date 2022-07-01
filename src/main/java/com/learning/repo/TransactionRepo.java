package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Transaction;
@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
