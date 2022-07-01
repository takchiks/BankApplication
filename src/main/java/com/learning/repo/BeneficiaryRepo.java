package com.learning.repo;

import com.learning.entity.Account;
import com.learning.entity.Beneficary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficiaryRepo extends JpaRepository<Beneficary,Integer> {
    public List<Beneficary> findByIsApproved(String isApproved);
}
