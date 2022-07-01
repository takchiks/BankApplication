package com.learning.service;

import java.util.List;

import com.learning.entity.Account;
import com.learning.entity.Admin;

public interface AdminService {
	public Admin addAdmin(Admin admin);
	public List<Admin> getAllAdmin();
	public Admin getAdminById(int personId);
	public Admin updateAdmin(Admin admin);
	
	
}
