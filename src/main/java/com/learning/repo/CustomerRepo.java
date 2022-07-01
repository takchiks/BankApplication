package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
