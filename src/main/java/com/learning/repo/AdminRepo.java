package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

}
