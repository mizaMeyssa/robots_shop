package com.robot.robots.resource;

import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robot.robots.domain.Cart;
import com.robot.robots.domain.User;


public class UserResource extends ResourceSupport{

	private final long id;
	private final String login;
	private final String password;
	private final Set<Cart> carts;
	
	public UserResource(User user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.carts = user.getCarts();
	}
	
	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public Set<Cart> getCarts() {
		return carts;
	}
	
	
}
