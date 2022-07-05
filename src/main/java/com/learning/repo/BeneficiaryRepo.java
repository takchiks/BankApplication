package com.learning.repo;

import com.learning.entity.Account;
import com.learning.entity.Beneficary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficary,Integer> {
    public List<Beneficary> findByIsApproved(String isApproved);
    public Beneficary findByAccountNumber(long accountNumber);
}
