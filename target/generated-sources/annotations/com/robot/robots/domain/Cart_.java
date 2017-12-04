package com.robot.robots.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cart.class)
public abstract class Cart_ {

	public static volatile SetAttribute<Cart, Purchase> puchases;
	public static volatile SingularAttribute<Cart, Long> id;
	public static volatile SingularAttribute<Cart, Timestamp> creation_date;
	public static volatile SingularAttribute<Cart, User> user;
	public static volatile SingularAttribute<Cart, Timestamp> payment_date;

}

