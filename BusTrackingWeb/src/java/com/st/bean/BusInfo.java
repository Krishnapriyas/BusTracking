package com.st.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus_info")
public class BusInfo extends org.apache.struts.action.ActionForm {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "bus_description")
    private String bus_description;
    @Column(name = "bus_name")
    private String bus_name;
    @Column(name = "bus_number")
    private String bus_number;
    @Column(name = "bus_status")
    private String bus_status;
    @Column(name = "lat")
    private String lat;
    @Column(name = "lng")
    private String lng;
    @Column(name = "owner_id")
    private int owner_id;

    public String getLat() {
        return lat;
    }

    public String getBus_status() {
        return bus_status;
    }

    public void setBus_status(String bus_status) {
        this.bus_status = bus_status;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBus_description(String bus_description) {
        this.bus_description = bus_description;
    }

    public String getBus_description() {
        return bus_description;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public String getBus_number() {
        return bus_number;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public BusInfo() {
        super();
    }
}
