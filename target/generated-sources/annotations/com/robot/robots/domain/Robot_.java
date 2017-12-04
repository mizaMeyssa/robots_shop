package com.robot.robots.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Robot.class)
public abstract class Robot_ {

	public static volatile SingularAttribute<Robot, Boolean> sold;
	public static volatile SingularAttribute<Robot, BigDecimal> price;
	public static volatile SingularAttribute<Robot, Long> qty;
	public static volatile SetAttribute<Robot, Purchase> puchases;
	public static volatile SingularAttribute<Robot, String> description;
	public static volatile SingularAttribute<Robot, Long> id;
	public static volatile SingularAttribute<Robot, String> label;

}

