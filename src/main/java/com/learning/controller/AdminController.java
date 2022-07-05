package com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/")
	public void addAdmin(@RequestBody Admin admin) {
		String encodedPassword = bCryptPasswordEncoder.encode(admin.getPassWord());
		admin.setPassWord(encodedPassword);
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
		String encodedPassword = bCryptPasswordEncoder.encode(admin.getPassWord());
		admin.setPassWord(encodedPassword);
		return adminService.updateAdmin(admin);
	}
	
	@DeleteMapping("/{adminId}")
	public String deleteAdminById(@PathVariable(name="adminId") int personId) {
		return adminService.deleteAdminById(personId);
	}
	
	@PostMapping("/authenticate")
	public String validateAdmin(@RequestBody UsernamePassword userPass) {
		String username = userPass.getUsername();
		String encodedPassword = bCryptPasswordEncoder.encode(userPass.getPassword());
		return adminService.validateAdmin(username, encodedPassword);
	}
	
	@PostMapping("/staff")
	public Staff createStaff(@RequestBody Staff staff) {
		String encodedPassword = bCryptPasswordEncoder.encode(staff.getPassWord());
		staff.setPassWord(encodedPassword);
		return adminService.createStaff(staff);
	}
	
	@GetMapping("/staff")
	public List<Staff> getAllStaff(){
		return adminService.getAllStaff();
	}
	
	@PutMapping("/staff")
	public ResponseEntity<String> setStaffStatus(@RequestBody StaffStatus staffStatus) {
		int staffId = staffStatus.getStaffId();
		Status status = staffStatus.getStatus();
		return new ResponseEntity<String>(adminService.setStaffStatus(staffId, status),HttpStatus.valueOf(200));
	}
}
