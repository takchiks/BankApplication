package com.learning.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.learning.entity.Staff;
import com.learning.enums.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.entity.Admin;
import com.learning.repo.AdminRepo;
import com.learning.repo.StaffRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private StaffRepo staffRepo;
   
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void addAdmin(Admin admin) {
		
		adminRepo.save(admin);
	}

	@Override
	public List<Admin> getAllAdmin() {
		
		return adminRepo.findAll();
	}

	@Override
	public Admin getAdminById(int adminId) {
		
		return adminRepo.findById(adminId).get();
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		
		return adminRepo.save(admin);
	}

	@Override
	public String deleteAdminById(int adminId) {
		adminRepo.deleteById(adminId);
		return "Admin deleted with id:" + adminId;
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
	public void setStaffStatus(int staffId, Status status) {
		Staff staff = staffRepo.findById(staffId).get();

		staff.setStatus(status);
		
	}


}
