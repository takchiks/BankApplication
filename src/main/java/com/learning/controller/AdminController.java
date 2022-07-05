package com.learning.controller;

import java.util.ArrayList;
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
import com.learning.repo.AdminRepo;
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
	public ResponseEntity<String> validateAdmin(@RequestBody UsernamePassword userPass) {
		String username = userPass.getUsername();
		String password = userPass.getPassword();

		List<Admin> admins = new ArrayList<Admin>();
		admins.addAll(adminService.getAllAdmin());
		
		for(Admin admin: admins) {
			if((admin.getUserName().equals(username)) && (bCryptPasswordEncoder.matches(password, admin.getPassWord()))) {
				return new ResponseEntity<String>("JWT Token", HttpStatus.OK);
			} 
		}
		return new ResponseEntity<String>("User details are incorrect", HttpStatus.FORBIDDEN);
	}
	
	@PostMapping("/staff")
	public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
		String encodedPassword = bCryptPasswordEncoder.encode(staff.getPassWord());
		staff.setPassWord(encodedPassword);
		
		List<Staff> staffArray = new ArrayList<Staff>();
		staffArray.addAll(adminService.getAllStaff());
		
		for(Staff s: staffArray) {
			if(s.getUserName().equals(staff.getUserName())) {
				return new ResponseEntity<Staff>(HttpStatus.FORBIDDEN);
			} 
		}
		
		return new ResponseEntity<Staff>(adminService.createStaff(staff), HttpStatus.OK);
	}
	
	@GetMapping("/staff")
	public ResponseEntity<List<Staff>> getAllStaff(){
		return new ResponseEntity<List<Staff>>(adminService.getAllStaff(), HttpStatus.OK);
	}
	
	@PutMapping("/staff")
	public ResponseEntity<String> setStaffStatus(@RequestBody StaffStatus staffStatus) {
		int staffId = staffStatus.getStaffId();
		Status status = staffStatus.getStatus();
		
		if((status==Status.DISABLE) || (status==Status.ENABLE)) {
			adminService.setStaffStatus(staffId, status);
			return new ResponseEntity<String>("Staff status changed",HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Staff status not changed",HttpStatus.BAD_REQUEST);
		}
	}
}
