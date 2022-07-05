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

	@Override
	public Beneficary findByAccountNumber(long accountNumber) {
		return beneficiaryRepo.findByAccountNumber(accountNumber);
	}

	@Override
	public Beneficary addBeneficiary(Beneficary beneficiary) {
		return beneficiaryRepo.save(beneficiary);
	}

	@Override
	public List<Beneficary> getAllBeneficiary() {
		return beneficiaryRepo.findAll();
	}

	@Override
	public Beneficary getBeneficiaryById(int benId) {
		return beneficiaryRepo.findById(benId).get();
	}

	@Override
	public Beneficary updateBeneficary(Beneficary beneficary) {
		return beneficiaryRepo.save(beneficary);
	}
	
	@Override
	public void deleteBeneficiary(int benId) {
		beneficiaryRepo.deleteById(benId);
	}
	
	
}
