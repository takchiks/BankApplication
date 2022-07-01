package com.learning.service;

import com.learning.entity.Account;
import com.learning.entity.Beneficary;

import java.util.List;

public interface BeneficiaryService {
    public List<Beneficary> findByIsApproved(String isApproved);
}
