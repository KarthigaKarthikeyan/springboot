package com.stacifysimple.springrestservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacifysimple.springrestservices.entities.User;
import com.stacifysimple.springrestservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) {
		//Optional is container object which may consists null or not null with exception handling 
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	public User updateUserByid(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
	    userRepository.deleteById(id);
		}
	}
	
	public User getUserByUsername(String userName) {
		return userRepository.findByUserName(userName);
	}
}
