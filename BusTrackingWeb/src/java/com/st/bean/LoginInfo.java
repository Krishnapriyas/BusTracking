package com.st.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.struts.upload.FormFile;

@Entity
@Table(name = "login_info")
 public class LoginInfo extends org.apache.struts.action.ActionForm {
 @Id
 @GeneratedValue
 @Column(name = "id")
 private  int id;
 @Column(name = "password")
 private  String password;
 @Column(name = "status")
 private  String status;
 @Column(name = "type")
 private  String type;
 @Column(name = "username")
 private  String username;
 public void setId( int id){
  this.id = id;
}
 public  int getId(){
  return id;
}
 public void setPassword( String password){
  this.password = password;
}
 public  String getPassword(){
  return password;
}
 public void setStatus( String status){
  this.status = status;
}
 public  String getStatus(){
  return status;
}
 public void setType( String type){
  this.type = type;
}
 public  String getType(){
  return type;
}
 public void setUsername( String username){
  this.username = username;
}
 public  String getUsername(){
  return username;
}

    public LoginInfo(){
        super();
    }
}
