package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.learning.entity.User;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Integer> {
	public User findByUserNameAndPassWord(String username, String password);
	//public User findByUserName(String username);
	public Optional<User> findByUserName(String username);

}
