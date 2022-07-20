package com.learning.service;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.learning.entity.Customer;
import com.learning.entity.User;

@CrossOrigin
public interface CustomerService {

	public Customer addCustomer(Customer customer);
	public List<Customer> getAllCustomer();
	public Customer getCustomerById(int personId);
	public Customer updateCustomer(Customer customer);
	public String getToken(String username, String password);
}
