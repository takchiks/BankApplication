package com.learning.service;

import java.util.List;

import com.learning.entity.Customer;
import com.learning.entity.User;

public interface CustomerService {

	public Customer addCustomer(Customer customer);
	public List<Customer> getAllCustomer();
	public Customer getCustomerById(int personId);
	public Customer updateCustomer(Customer customer);
	
}
