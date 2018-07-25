package com.st.bean;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BusInfo.class)
public abstract class BusInfo_ {

	public static volatile SingularAttribute<BusInfo, String> bus_description;
	public static volatile SingularAttribute<BusInfo, String> lng;
	public static volatile SingularAttribute<BusInfo, Integer> owner_id;
	public static volatile SingularAttribute<BusInfo, String> bus_status;
	public static volatile SingularAttribute<BusInfo, Integer> id;
	public static volatile SingularAttribute<BusInfo, String> bus_number;
	public static volatile SingularAttribute<BusInfo, String> bus_name;
	public static volatile SingularAttribute<BusInfo, String> lat;

}

