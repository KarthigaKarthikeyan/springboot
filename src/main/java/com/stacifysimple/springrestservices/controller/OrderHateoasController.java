package com.stacifysimple.springrestservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacifysimple.springrestservices.entities.Order;
import com.stacifysimple.springrestservices.entities.User;
import com.stacifysimple.springrestservices.exception.UserNotFoundException;
import com.stacifysimple.springrestservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private UserRepository userRepository;


	// get All Orders for a user

	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		List<Order> allorders =  userOptional.get().getOrders();
		 return CollectionModel.of(allorders);
		
	}
}
