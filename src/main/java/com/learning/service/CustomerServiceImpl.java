package com.learning.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.learning.entity.Customer;
import com.learning.entity.User;
import com.learning.repo.CustomerRepo;
import com.learning.repo.UserRepo;
@CrossOrigin
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Customer addCustomer(Customer customer) {
		String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassWord());
		customer.setPassWord(encodedPassword);
		return customerRepo.save(customer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		
		return customerRepo.findAll();
	}

	@Override
	public Customer getCustomerById(int personId) {
		
		return customerRepo.findById(personId).get();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassWord());
		customer.setPassWord(encodedPassword);
		return customerRepo.save(customer);
	}
	
	@Override
	public String getToken(String username, String password) {
		
		String token = " ";
		User user = userRepo.findByUserName(username).get();
		if (user.getUserName().equals(username) && user.getPassWord().equals(password)) {
				token = username;
			}
		
		return token;
	}
	

}
