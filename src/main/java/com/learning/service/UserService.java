package com.learning.service;

import java.util.List;

import com.learning.entity.Account;
import com.learning.entity.User;

public interface UserService {

	public User addUser(User user);
	public List<User> getAllUser();
	public User getUserById(int personId);
	public User updateUser(User user);
}
