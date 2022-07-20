package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.learning.entity.Customer;
@CrossOrigin
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
