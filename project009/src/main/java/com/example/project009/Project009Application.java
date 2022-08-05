package com.example.project009;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.project009.DAO.UserRepository;
import com.example.project009.daoCLass.User;

@SpringBootApplication
public class Project009Application extends SpringBootServletInitializer{
	@Autowired
	UserRepository userRepo;
	
	@PostConstruct
	public void addUsers() {
		List<User> l=Stream.of(
				new User(1,"sai","password","sai@gmail.com"),
				new User(2,"krishna","password","krishna@gmail.com")
				).collect(Collectors.toList());
		userRepo.saveAll(l);
				
		System.out.println(userRepo.findAll());		
				
				
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Project009Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Project009Application.class, args);
	}

}
