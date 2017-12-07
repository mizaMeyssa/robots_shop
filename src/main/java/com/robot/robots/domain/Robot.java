package com.robot.robots.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "robot")
public class Robot implements Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "robot_gen")
	@SequenceGenerator(name = "robot_gen", sequenceName = "robot_id_seq")
    private Long id;
	
	@OneToMany(mappedBy = "robot")
	private Set<Purchase> puchases;
	
	@Column(name = "label", nullable= false)
    private String label;

	@Column(name = "description")
    private String description;

	@Column(name = "price", nullable= false)
    private BigDecimal price;
	
	@Transient
	@Formula(value = "case when qty=0 then true else false")
    private boolean sold = false;
	
	@Column(name = "qty", nullable= false)
    private long qty = 0;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

	public boolean isSold() {
		return sold;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public void set(Robot robot){
    	this.setDescription(robot.getDescription());
    	this.setLabel(robot.getLabel());
    	this.setPrice(robot.getPrice());
    	this.setQty(robot.getQty());
    }

}
