package com.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learning.repo.AdminRepo;

@SpringBootApplication
public class BankApplication implements CommandLineRunner{

	@Autowired
	private AdminRepo adminRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
