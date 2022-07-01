package com.learning.service;

import java.util.List;

import com.learning.entity.Staff;
import com.learning.entity.User;

public interface StaffService {

	public Staff addStaff(Staff staff);
	public List<Staff> getAllStaff();
	public Staff getStaffById(int userId);
	public Staff updateStaff(Staff staff);
	
}