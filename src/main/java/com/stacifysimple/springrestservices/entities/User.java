package com.stacifysimple.springrestservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This is User Model")
//pojo represents a table in /db
@Entity
//denotes the table name and schema (table seggrated within appln eg:,schema="usrmgmt")
@Table(name = "user")
@JsonFilter(value = "userFilter")
public class User extends RepresentationModel<User>{
	@ApiModelProperty(notes = "Auto generated field",required = true,position = 1)
	@Id
	@GeneratedValue
	private Long userId;
	
	@NotEmpty(message="mandatory username")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	private String userName;
	
	@Size(min=2,message="name have max 10chars")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstName;
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName;
	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
	private String email;
	@Column(name = "ROLE", length = 50, nullable = false)
	private String role;
	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	private String ssn;
	
	@OneToMany(mappedBy="user")
	private List<Order> orders;

	// No arg Constructor
	public User() {

	}

	public User(Long userId, String userName, String firstName, String lastName, String email, String role, String ssn) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

}
