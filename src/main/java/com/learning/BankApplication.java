package com.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.learning.repo.AdminRepo;


@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200")
public class BankApplication{

	@Autowired
	private AdminRepo adminRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
		
		
	}
	



}
