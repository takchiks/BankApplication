package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learning.entity.Staff;
import com.learning.repo.StaffRepo;

public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepo  staffRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Staff addStaff(Staff staff) {
		String encodedPassword = bCryptPasswordEncoder.encode(staff.getPassWord());
		staff.setPassWord(encodedPassword);
		return staffRepo.save(staff);
	}

	@Override
	public List<Staff> getAllStaff() {
		
		return staffRepo.findAll();
	}

	@Override
	public Staff getStaffById(int userId) {
		
		return staffRepo.findById(userId).get();
	}

	@Override
	public Staff updateStaff(Staff staff) {
		String encodedPassword = bCryptPasswordEncoder.encode(staff.getPassWord());
		staff.setPassWord(encodedPassword);
		return staffRepo.save(staff);
	}

}
