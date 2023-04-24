package com.pheriwala.app.models;

public class Vendors {

    String id, name, phone,mail,location;

    public Vendors() {
    }

    public Vendors(String id, String name, String phone, String mail, String location) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
