package com.stacifysimple.springrestservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacifysimple.springrestservices.entities.User;
import com.stacifysimple.springrestservices.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id);
	}
	
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id")Long id,@RequestBody User user) {
		return userService.updateUserByid(id, user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteuserbyId(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	@GetMapping("/users/byusername/{userName}") 
	public User getUserByUsername(@PathVariable("userName")String userName) {
		
		return userService.getUserByUsername(userName);
	}

}
