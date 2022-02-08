package com.stacifysimple.springrestservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
/*Spring Boot starter Project*/
@SpringBootApplication
public class SpringbootrestservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootrestservicesApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.US);
		return cookieLocaleResolver;
	}
	
	@Bean
	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
		bundleMessageSource.addBasenames("messages");
		return bundleMessageSource;
	}

}
