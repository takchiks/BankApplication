package com.learning.service;

import java.util.List;
import com.learning.entity.Staff;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.entity.Admin;
import com.learning.repo.AdminRepo;
import com.learning.repo.StaffRepo;

@Service
 
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private StaffRepo staffRepo;
   
	@Override
	public void addAdmin(Admin admin) {
		
		adminRepo.save(admin);
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

	@Override
	public String deleteAdminById(int personId) {
		adminRepo.deleteById(personId);
		return "Admin deleted with id:" + personId;
	}

	@Override
	public String validateAdmin(String userName, String password) {
		
		return null;
	}
	
	@Override
	public Staff createStaff(Staff staff) {
		return staffRepo.save(staff);
	}

	@Override
	public List<Staff> getAllStaff() {
		return staffRepo.findAll();
	}

	@Override
	public String setStaffStatus() {
		// TODO Auto-generated method stub
		return null;
	}


}
