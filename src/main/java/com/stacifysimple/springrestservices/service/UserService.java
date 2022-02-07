package com.stacifysimple.springrestservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacifysimple.springrestservices.entities.User;
import com.stacifysimple.springrestservices.exception.UserExistsException;
import com.stacifysimple.springrestservices.exception.UserNotFoundException;
import com.stacifysimple.springrestservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) throws UserExistsException {
		User existUser = userRepository.findByUserName(user.getUserName());
		if(existUser !=null) {
			throw new UserExistsException("user already exception");
		}
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		//Optional is container object which may consists null or not null with exception handling 
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in repository");
		}
		return user;
	}
	
	public User updateUserByid(Long id, User user) throws UserNotFoundException {
		//Optional is container object which may consists null or not null with exception handling 
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in repository provide correct id");
		}
		user.setUserId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user not found");
		}
	    userRepository.deleteById(id);
	}
	
	public User getUserByUsername(String userName) {
		return userRepository.findByUserName(userName);
	}
}
