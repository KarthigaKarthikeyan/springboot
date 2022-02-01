package com.stacifysimple.springrestservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	//@RequestMapping(method = RequestMethod.GET,path="/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "HelloWorld";
	}
	
	@GetMapping("/helloWorldBean")
	public UserDetails helloWorldBean() {
		
		return new UserDetails("Karthiga", "K", "Chennai");
	}

}
