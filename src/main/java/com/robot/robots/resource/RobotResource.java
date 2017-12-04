package com.robot.robots.resource;

import java.math.BigDecimal;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robot.robots.domain.Robot;

public class RobotResource extends ResourceSupport {

    private final long id;
    private final String label;
    private final String description;
    private final BigDecimal price;
    private final boolean sold;
    private final long qty;

    public RobotResource(Robot robot) {
        id = robot.getId();
        label = robot.getLabel();
        description = robot.getDescription();
        price = robot.getPrice();
        sold = robot.isSold();
        qty = robot.getQty();
    }

    @JsonProperty("id")
    public Long getResourceId() {
        return id;
    }
    
    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
    
    public boolean isSold() {
        return sold;
    }

	public long getQty() {
		return qty;
	}
}
