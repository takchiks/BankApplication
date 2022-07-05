package com.learning.service;

import com.learning.entity.Account;
import com.learning.entity.Beneficary;

import java.util.List;

public interface BeneficiaryService {
	
	public Beneficary addBeneficiary(Beneficary beneficiary);
	public List<Beneficary> getAllBeneficiary();
	public Beneficary getBeneficiaryById(int benId);
	public Beneficary updateBeneficary(Beneficary beneficary);
    public List<Beneficary> findByIsApproved(String isApproved);
<<<<<<< HEAD
    public void deleteBeneficiary(int benId);
    
=======
	public Beneficary findByAccountNumber(long accountNumber);
>>>>>>> branch 'master' of https://github.com/takchiks/BankApplication
}
