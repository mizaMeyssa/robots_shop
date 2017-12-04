package com.robot.robots.domain;

public interface Identifiable extends org.springframework.hateoas.Identifiable<Long> {

    public void setId(Long id);

}