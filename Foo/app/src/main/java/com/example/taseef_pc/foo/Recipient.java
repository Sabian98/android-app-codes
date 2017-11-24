package com.example.taseef_pc.foo;

import java.io.Serializable;

/**
 * Created by Taseef-PC on 2/8/2017.
 */


public class Recipient implements Serializable {
    private String name;
    private String location;
    private String contact;

    public String getUserName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setcontact(String contact) {
        this.contact = contact;
    }
}
