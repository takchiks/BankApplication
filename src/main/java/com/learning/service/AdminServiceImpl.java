package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.entity.Admin;
import com.learning.repo.AdminRepo;

@Service
 
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;
   
	@Override
	public Admin addAdmin(Admin admin) {
		
		return adminRepo.save(admin);
	}

	@Override
	public List<Admin> getAllAdmin() {
		
		return adminRepo.findAll();
	}

	@Override
	public Admin getAdminById(int personId) {
		
		return adminRepo.findById(personId).get();
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		
		return adminRepo.save(admin);
	}

}
