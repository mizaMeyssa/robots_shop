package com.robot.robots.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "access_right")
public class Right implements Identifiable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "right_gen")
	@SequenceGenerator(name = "right_gen", sequenceName = "right_id_seq")
	private Long id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "label")
	private String label;
	
	@ManyToMany(mappedBy = "rights")
	private Set<Profile> profiles;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
