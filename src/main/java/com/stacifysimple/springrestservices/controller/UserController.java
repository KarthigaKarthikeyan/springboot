package com.stacifysimple.springrestservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacifysimple.springrestservices.entities.User;
import com.stacifysimple.springrestservices.exception.UserExistsException;
import com.stacifysimple.springrestservices.exception.UserNameNotFoundException;
import com.stacifysimple.springrestservices.exception.UserNotFoundException;
import com.stacifysimple.springrestservices.service.UserService;

@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user,UriComponentsBuilder uriComponentsBuilder) {
		try {
			userService.createUser(user);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(httpHeaders,HttpStatus.CREATED);
			
			
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id")Long id,@RequestBody User user) {
		try {
			return userService.updateUserByid(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteuserbyId(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	@GetMapping("/byusername/{userName}") 
	public User getUserByUsername(@PathVariable("userName")String userName) throws UserNameNotFoundException {
		
		User user =  userService.getUserByUsername(userName);
		if(user == null) {
			throw new UserNameNotFoundException("Username"+userName +" User name not found in repository");
		}
		return user;
	}

}
