package com.learning.controller;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Admin;
import com.learning.entity.Staff;
import com.learning.enums.Status;
import com.learning.others.StaffStatus;
import com.learning.others.UsernamePassword;
import com.learning.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/")
	public void addAdmin(@RequestBody Admin admin) {
		adminService.addAdmin(admin);
	}
	
	@GetMapping("/all")
	public List<Admin> getAllAdmin(){
		return adminService.getAllAdmin();
	}
	
	@GetMapping("/{adminId}")
	public Admin getAdminById(@PathVariable(name = "adminId") int personId) {
		return adminService.getAdminById(personId);
	}
	
	@PutMapping("/")
	public Admin updateAdmin(@RequestBody Admin admin) {
		return adminService.updateAdmin(admin);
	}
	
	@DeleteMapping("/{adminId}")
	public String deleteAdminById(@PathVariable(name="adminId") int personId) {
		return adminService.deleteAdminById(personId);
	}
	
	@PostMapping("/authenticate")
	public String validateAdmin(@RequestBody UsernamePassword up) {
		String password = up.getPassword();
		String username = up.getUsername();
		return adminService.validateAdmin(username, password);
	}
	
	@PostMapping("/staff")
	public Staff createStaff(@RequestBody Staff staff) {
		return adminService.createStaff(staff);
	}
	
	@GetMapping("/staff")
	public List<Staff> getAllStaff(){
		return adminService.getAllStaff();
	}
	
	@PutMapping("/staff")
	public String setStaffStatus(@RequestBody StaffStatus ss) {
		int staffId = ss.getStaffId();
		Status status = ss.getStatus();
		return adminService.setStaffStatus(staffId, status);
	}
}
