package com.st.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.struts.upload.FormFile;

@Entity
@Table(name = "stops_info")
 public class StopsInfo extends org.apache.struts.action.ActionForm {
 @Id
 @GeneratedValue
 @Column(name = "id")
 private  int id;
 @Column(name = "stop_name")
 private  String stop_name;
 public void setId( int id){
  this.id = id;
}
 public  int getId(){
  return id;
}
 public void setStop_name( String stop_name){
  this.stop_name = stop_name;
}
 public  String getStop_name(){
  return stop_name;
}

    public StopsInfo(){
        super();
    }
}
