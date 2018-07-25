package com.st.bean;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BusTripInfo.class)
public abstract class BusTripInfo_ {

	public static volatile SingularAttribute<BusTripInfo, String> trip_time;
	public static volatile SingularAttribute<BusTripInfo, Integer> stop_num;
	public static volatile SingularAttribute<BusTripInfo, Integer> id;
	public static volatile SingularAttribute<BusTripInfo, String> bus_stop;
	public static volatile SingularAttribute<BusTripInfo, Integer> bus_id;
	public static volatile SingularAttribute<BusTripInfo, String> bus_start;

}

