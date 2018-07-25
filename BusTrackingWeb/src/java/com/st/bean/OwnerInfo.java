package com.st.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "owner_info")
public class OwnerInfo extends org.apache.struts.action.ActionForm {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "login_id")
    private int login_id;
    @Column(name = "owner_address")
    private String owner_address;
    @Column(name = "owner_email")
    private String owner_email;
    @Column(name = "owner_name")
    private String owner_name;
    @Column(name = "owner_phone")
    private String owner_phone;
    
    transient private String username;
   
    transient private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }

    public int getLogin_id() {
        return login_id;
    }

    public String getOwner_address() {
        return owner_address;
    }

    public void setOwner_address(String owner_address) {
        this.owner_address = owner_address;
    }

    public String getOwner_email() {
        return owner_email;
    }

    public void setOwner_email(String owner_email) {
        this.owner_email = owner_email;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public OwnerInfo() {
        super();
    }
}
