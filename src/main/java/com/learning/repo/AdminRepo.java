package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Admin;
<<<<<<< HEAD
@Repository
=======
import com.learning.entity.Staff;

>>>>>>> 2bbaea7cb299aabc96897db58cd2c502aeda653b
public interface AdminRepo extends JpaRepository<Admin, Integer>{

	Staff save(Staff staff);

}
