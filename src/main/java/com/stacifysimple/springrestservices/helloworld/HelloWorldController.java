package com.stacifysimple.springrestservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource bundleMessageSource;
	
	//@RequestMapping(method = RequestMethod.GET,path="/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "HelloWorld";
	}
	
	@GetMapping("/helloWorldBean")
	public UserDetails helloWorldBean() {
		
		return new UserDetails("Karthiga", "K", "Chennai");
	}
	
	@GetMapping("/hello-i18")
	public String getMessageInI18nFormat(@RequestHeader(name="Accept-Language",required=false) String locale) {
		return bundleMessageSource.getMessage("label.hello", null,new Locale(locale));
	}
	

	@GetMapping("/hello-i18n")
	public String getMessageInI18nFormat() {
		return bundleMessageSource.getMessage("label.hello", null,LocaleContextHolder.getLocale());
	}
	
}
