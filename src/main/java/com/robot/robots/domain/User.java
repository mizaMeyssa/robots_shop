package com.robot.robots.domain;

import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "app_user")
public class User implements Identifiable{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_gen")
		@SequenceGenerator(name = "user_gen", sequenceName = "user_id_seq")
		private Long id;
		
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		private Set<Cart> carts;
		
		@ManyToOne
		@JoinColumn(name = "profile_id", referencedColumnName = "id")
		private Profile profile;
		
		@Column(name = "login", nullable = false)
		private String login;
		
		@Column(name = "password", nullable = false)
		private String password;

		@Override
		public Long getId() {
			return id;
		}

		@Override
		public void setId(Long id) {
			this.id = id;
		}
		
		public Set<Cart> getCarts() {
			return carts;
		}

		public void setCarts(Set<Cart> carts) {
			this.carts = carts;
		}

		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile profile) {
			this.profile = profile;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public void set(User user) {
			this.setId(user.getId());
			this.setLogin(user.getLogin());
			this.setPassword(user.getPassword());
		}
	
}
