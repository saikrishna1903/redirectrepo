package com.example.project009.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project009.daoCLass.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	User findByUserName(String username);

}
