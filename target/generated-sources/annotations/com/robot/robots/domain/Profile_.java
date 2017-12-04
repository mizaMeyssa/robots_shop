package com.robot.robots.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Profile.class)
public abstract class Profile_ {

	public static volatile SingularAttribute<Profile, String> code;
	public static volatile SetAttribute<Profile, Right> rights;
	public static volatile SingularAttribute<Profile, Long> id;
	public static volatile SingularAttribute<Profile, String> label;
	public static volatile SetAttribute<Profile, User> users;

}

