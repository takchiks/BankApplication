package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
