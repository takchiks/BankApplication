package com.learning.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.learning.entity.User;
import com.learning.enums.RoleType;
import com.learning.enums.Status;
import com.learning.others.StaffStatus;
import com.learning.others.UsernamePassword;
import com.learning.pojo.ErrorMapper;
import com.learning.pojo.TokenPojo;
import com.learning.repo.AdminRepo;
import com.learning.repo.UserRepo;
import com.learning.securtiy.JWTUtil;
import com.learning.service.AdminService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	AdminService adminService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/")
	public void addAdmin(@RequestBody Admin admin) {
		String encodedPassword = bCryptPasswordEncoder.encode(admin.getPassWord());
		admin.setPassWord(encodedPassword);
		adminService.addAdmin(admin);
	}

	@PreAuthorize("hasAuthority('ADMIN') ")
	@GetMapping("/all")
	public List<Admin> getAllAdmin() {
		return adminService.getAllAdmin();
	}

	@PreAuthorize("hasAuthority('ADMIN') ")
	@GetMapping("/{adminId}")
	public Admin getAdminById(@PathVariable(name = "adminId") int personId) {
		return adminService.getAdminById(personId);
	}

	@PreAuthorize("hasAuthority('ADMIN') ")
	@PutMapping("/")
	public Admin updateAdmin(@RequestBody Admin admin) {
		String encodedPassword = bCryptPasswordEncoder.encode(admin.getPassWord());
		admin.setPassWord(encodedPassword);
		return adminService.updateAdmin(admin);
	}

	@PreAuthorize("hasAuthority('ADMIN') ")
	@DeleteMapping("/{adminId}")
	public String deleteAdminById(@PathVariable(name = "adminId") int personId) {
		return adminService.deleteAdminById(personId);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, Object>> authenticate(@RequestBody UsernamePassword body) {

		try {
			System.out.println(body);
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getUsername(), body.getPassword());

			authManager.authenticate(authInputToken);

			RoleType role = userRepo.findByUserName(body.getUsername()).get().getRole();

			if (role != RoleType.ADMIN) {
				return new ResponseEntity(new ErrorMapper("NOT ADMIN!! CHECK USER"), HttpStatus.BAD_REQUEST);
			}

			String token = jwtUtil.generateToken(body.getUsername());

			return new ResponseEntity<>(Collections.singletonMap("jwt", token), HttpStatus.ACCEPTED);
		} catch (AuthenticationException authExc) {
			return new ResponseEntity(new ErrorMapper("WRONG USERNAME OR PASSWORD"), HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAuthority('STAFF') or hasAuthority('ADMIN') ")
	@PostMapping(value = "/staff", consumes = "application/json")
	public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
		System.out.println(staff);
		String encodedPassword = bCryptPasswordEncoder.encode(staff.getPassWord());
		staff.setPassWord(encodedPassword);

		List<Staff> staffArray = new ArrayList<Staff>();
		staffArray.addAll(adminService.getAllStaff());

		for (Staff s : staffArray) {
			if (s.getUserName().equals(staff.getUserName())) {
				return new ResponseEntity<Staff>(HttpStatus.FORBIDDEN);
			}
		}

		return new ResponseEntity<Staff>(adminService.createStaff(staff), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/staff")
	public ResponseEntity<List<Staff>> getAllStaff() {
		return new ResponseEntity<List<Staff>>(adminService.getAllStaff(), HttpStatus.OK);
	}

	@PutMapping("/staff")
	public ResponseEntity<User> setStaffStatus(@RequestBody Staff staffStatus) {
		int staffId = staffStatus.getUserId();
		Status status = staffStatus.getStatus();
		System.out.println(staffStatus);
		if ((status == Status.DISABLE) || (status == Status.ENABLE)) {
			adminService.setStaffStatus(staffId, status);
			return new ResponseEntity<User>(staffStatus, HttpStatus.OK);
		} else {
			return new ResponseEntity(new ErrorMapper("Staff status not changed"), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/getuser")
	public ResponseEntity<Admin> getUser(@RequestBody TokenPojo body) {

		try {
			System.out.println(body);

			String user = jwtUtil.validateTokenAndRetrieveSubject(body.getToken());

			System.out.println(user);
			Admin userToken = adminService.getAdminById(userRepo.findByUserName(user).get().getUserId());

			return new ResponseEntity<>(userToken, HttpStatus.ACCEPTED);
		} catch (Exception authExc) {
			authExc.printStackTrace();
		}
		return new ResponseEntity(new ErrorMapper("WRONG CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}
}
