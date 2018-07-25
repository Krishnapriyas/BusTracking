package com.st.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.struts.upload.FormFile;

@Entity
@Table(name = "driver_info")
 public class DriverInfo extends org.apache.struts.action.ActionForm {
 @Id
 @GeneratedValue
 @Column(name = "id")
 private  int id;
 @Column(name = "login_id")
 private  int login_id;
 @Column(name = "driver_name")
 private  String driver_name;
 @Column(name = "driver_phone")
 private  String driver_phone;
 @Column(name = "driver_email")
 private  String driver_email;
 @Column(name = "driver_address")
 private  String driver_address;
 @Column(name = "bus_id")
 private  int bus_id;
 public void setId( int id){
  this.id = id;
}
 public  int getId(){
  return id;
}
 public void setLogin_id( int login_id){
  this.login_id = login_id;
}
 public  int getLogin_id(){
  return login_id;
}
 public void setDriver_name( String driver_name){
  this.driver_name = driver_name;
}
 public  String getDriver_name(){
  return driver_name;
}
 public void setDriver_phone( String driver_phone){
  this.driver_phone = driver_phone;
}
 public  String getDriver_phone(){
  return driver_phone;
}
 public void setDriver_email( String driver_email){
  this.driver_email = driver_email;
}
 public  String getDriver_email(){
  return driver_email;
}
 public void setDriver_address( String driver_address){
  this.driver_address = driver_address;
}
 public  String getDriver_address(){
  return driver_address;
}
 public void setBus_id( int bus_id){
  this.bus_id = bus_id;
}
 public  int getBus_id(){
  return bus_id;
}

    public DriverInfo(){
        super();
    }
}
