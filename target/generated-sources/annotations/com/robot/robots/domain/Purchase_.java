package com.robot.robots.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Purchase.class)
public abstract class Purchase_ {

	public static volatile SingularAttribute<Purchase, Robot> robot;
	public static volatile SingularAttribute<Purchase, BigDecimal> price;
	public static volatile SingularAttribute<Purchase, Long> qty;
	public static volatile SingularAttribute<Purchase, Long> id;
	public static volatile SingularAttribute<Purchase, Cart> cart;

}

