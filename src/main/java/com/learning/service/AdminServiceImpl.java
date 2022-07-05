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
	public String validateAdmin(String username, String password) {
		List<Admin> admins = new ArrayList<Admin>();
		admins.addAll(adminRepo.findAll());
		String msg = null;

		for(Admin admin: admins) {
			if((admin.getUserName().equals(username))&& (BCrypt.checkpw(password, admin.getPassWord()))) {
				msg = "JWT Token";
				break;
			} else {
				msg = "User details incorrect";
			}
		}
		return msg;
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
	public String setStaffStatus(int staffId, Status status) {
		Staff staff = staffRepo.findById(staffId).get();

		staff.setStatus(status);
		/*
		 * if((status!=Status.DISABLE) || (status!=status.ENABLE)) { throw new
		 * AccountStatusException("Staff status not changed"); }
		 */
		return "staff saved";
	}


}
