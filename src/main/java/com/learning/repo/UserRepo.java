package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
