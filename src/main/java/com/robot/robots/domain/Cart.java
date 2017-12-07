package com.robot.robots.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart implements Identifiable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cart_gen")
	@SequenceGenerator(name = "cart_gen", sequenceName = "cart_id_seq", allocationSize = 1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user; // unidirectional
	
	@OneToMany(mappedBy = "cart")
	private Set<Purchase> puchases;
	
	@Column(name = "creation_date")
	private Timestamp creation_date;
	
	@Column(name = "payment_date")
	private Timestamp payment_date;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Purchase> getPuchases() {
		return puchases;
	}

	public void setPuchases(Set<Purchase> puchases) {
		this.puchases = puchases;
	}

	public Timestamp getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}

	public Timestamp getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Timestamp payment_date) {
		this.payment_date = payment_date;
	}
	
	
	
}
