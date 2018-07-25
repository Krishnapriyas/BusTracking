package com.st.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus_trip_info")
public class BusTripInfo extends org.apache.struts.action.ActionForm {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "bus_start")
    private String bus_start;
    @Column(name = "bus_stop")
    private String bus_stop;
    @Column(name = "trip_time")
    private String trip_time;
    transient private String trip_end_time;
    @Column(name = "bus_id")
    private int bus_id;
    @Column(name = "stop_num")
    private int stop_num;

    public int getStop_num() {
        return stop_num;
    }

    public void setStop_num(int stop_num) {
        this.stop_num = stop_num;
    }

    public String getTrip_end_time() {
        return trip_end_time;
    }

    public void setTrip_end_time(String trip_end_time) {
        this.trip_end_time = trip_end_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBus_start(String bus_start) {
        this.bus_start = bus_start;
    }

    public String getBus_start() {
        return bus_start;
    }

    public void setBus_stop(String bus_stop) {
        this.bus_stop = bus_stop;
    }

    public String getBus_stop() {
        return bus_stop;
    }

    public void setTrip_time(String trip_time) {
        this.trip_time = trip_time;
    }

    public String getTrip_time() {
        return trip_time;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }

    public int getBus_id() {
        return bus_id;
    }

    public BusTripInfo() {
        super();
    }
}
