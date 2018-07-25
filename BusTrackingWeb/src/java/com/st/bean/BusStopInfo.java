package com.st.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus_stop_info")
public class BusStopInfo extends org.apache.struts.action.ActionForm {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "stop_id")
    private int stop_id;
    @Column(name = "trip_id")
    private int trip_id;
    @Column(name = "stop_order")
    private int stop_order;
    @Column(name = "stop_time")
    private String stop_time;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStop_id(int stop_id) {
        this.stop_id = stop_id;
    }

    public int getStop_id() {
        return stop_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public int getStop_order() {
        return stop_order;
    }

    public void setStop_order(int stop_order) {
        this.stop_order = stop_order;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public BusStopInfo() {
        super();
    }
}
