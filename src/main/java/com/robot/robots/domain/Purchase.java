package com.robot.robots.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase implements Identifiable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn( name = "robot_id", referencedColumnName = "id")
	private Robot robot;
	
	@ManyToOne
	@JoinColumn( name = "cart_id", referencedColumnName = "id")
	private Cart cart;
	
	@Column( name = "qty")
	private Long qty;
	
	/*
	 *  This is only persisted when the customer is paying for the purchase.
	 */
	@Column( name = "price")
	private BigDecimal price;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public BigDecimal getTentative_price() {
		return price;
	}

	public void setTentative_price(BigDecimal price) {
		this.price = price;
	}	

}
