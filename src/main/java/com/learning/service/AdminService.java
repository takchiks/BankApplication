package com.learning.service;

import java.util.List;


import com.learning.entity.Admin;
import com.learning.entity.Staff;
import com.learning.enums.Status;

public interface AdminService {
	public void addAdmin(Admin admin);
	public List<Admin> getAllAdmin();
	public Admin getAdminById(int personId);
	public Admin updateAdmin(Admin admin);
	public String deleteAdminById(int personId);
	public Staff createStaff(Staff staff);
	public List<Staff> getAllStaff();
	public void setStaffStatus(int personId, Status status);	
}
