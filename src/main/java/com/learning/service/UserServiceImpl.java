package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.User;
import com.learning.repo.UserRepo;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Override
	public User addUser(User user) {
	
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepo.findAll();
	}

	@Override
	public User getUserById(int userId) {
		
		return userRepo.findById(userId).get();
	}

	@Override
	public User updateUser(User user) {
		
		return userRepo.save(user);
	}

}
