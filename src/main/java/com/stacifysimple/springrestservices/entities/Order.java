package com.stacifysimple.springrestservices.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="orders")
public class Order extends RepresentationModel<Order> {
	
	@Id
	@GeneratedValue
	private Long id;
	private String orderdesc;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderdesc() {
		return orderdesc;
	}

	public void setOrderdesc(String orderdesc) {
		this.orderdesc = orderdesc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
