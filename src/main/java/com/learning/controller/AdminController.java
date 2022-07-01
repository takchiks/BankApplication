package com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Admin;
import com.learning.service.AdminService;
import com.learning.service.StaffService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	//@Autowired
	//StaffService staffService;
	
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
}
