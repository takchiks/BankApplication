package com.learning.service;

import com.learning.entity.Account;
import com.learning.entity.Beneficary;
import com.learning.repo.BeneficiaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{
    @Autowired
    private BeneficiaryRepo beneficiaryRepo;

    @Override
    public List<Beneficary> findByIsApproved(String isApproved) {
        return beneficiaryRepo.findByIsApproved(isApproved);
    }
}
