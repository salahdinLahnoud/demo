package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService{
	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	public List<User> list(){
		return userRepo.findAll();				
	}
	
	public User save(User u) {
		return userRepo.save(u);
	}
	public List<User> save(List<User> u){
		return userRepo.saveAll(u);
	}
}
