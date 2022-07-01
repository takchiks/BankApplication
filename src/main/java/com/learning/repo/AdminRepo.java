package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.Admin;
import com.learning.entity.Staff;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

	Staff save(Staff staff);

}
