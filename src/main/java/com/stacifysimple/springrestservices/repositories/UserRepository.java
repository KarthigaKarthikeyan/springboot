package com.stacifysimple.springrestservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacifysimple.springrestservices.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);
		
}
