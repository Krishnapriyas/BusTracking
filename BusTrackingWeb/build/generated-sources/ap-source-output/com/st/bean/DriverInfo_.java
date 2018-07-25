package com.st.bean;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DriverInfo.class)
public abstract class DriverInfo_ {

	public static volatile SingularAttribute<DriverInfo, String> driver_name;
	public static volatile SingularAttribute<DriverInfo, String> driver_address;
	public static volatile SingularAttribute<DriverInfo, Integer> login_id;
	public static volatile SingularAttribute<DriverInfo, String> driver_email;
	public static volatile SingularAttribute<DriverInfo, Integer> id;
	public static volatile SingularAttribute<DriverInfo, Integer> bus_id;
	public static volatile SingularAttribute<DriverInfo, String> driver_phone;

}

