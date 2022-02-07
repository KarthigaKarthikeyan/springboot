package com.stacifysimple.springrestservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacifysimple.springrestservices.entities.Order;
import com.stacifysimple.springrestservices.entities.User;
import com.stacifysimple.springrestservices.exception.UserNotFoundException;
import com.stacifysimple.springrestservices.repositories.OrderRepository;
import com.stacifysimple.springrestservices.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> optionlUser = userRepository.findById(userid);

		if (!optionlUser.isPresent()) {
			throw new UserNotFoundException("user not found");
		}

		return optionlUser.get().getOrders();
	}

	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> optionlUser = userRepository.findById(userid);

		if (!optionlUser.isPresent()) {
			throw new UserNotFoundException("user not found");
		}
		
		User user = optionlUser.get();
		order.setUser(user);
		return orderRepository.save(order);
		
		
	}
}
