package com.stacifysimple.springrestservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacifysimple.springrestservices.entities.Order;
import com.stacifysimple.springrestservices.entities.User;
import com.stacifysimple.springrestservices.exception.UserNotFoundException;
import com.stacifysimple.springrestservices.service.UserService;
@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	
	@Autowired
	private UserService userService;
	
	// getUserById
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOptional =  userService.getUserById(id);
			User user = userOptional.get();
			Long userid = user.getUserId();
			Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			
			
			return EntityModel.of(user, selflink);			
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}
	
	// getAllUsers Method
	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> allusers = userService.getAllUsers();
		
		for(User user : allusers) {
			//Self Link 
			Long userid = user.getUserId();
			Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			
			//Relationship link with getAllOrders
			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
					.getAllOrders(userid);
			Link orderslink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(orderslink);
			
		}
		//Self link for getAllUsers
		Link selflinkgetAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		return CollectionModel.of(allusers, selflinkgetAllUsers);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
