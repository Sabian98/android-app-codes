package com.example.taseef_pc.foo;

import java.io.Serializable;

/**
 * Created by Taseef-PC on 2/8/2017.
 */


public class Donor implements Serializable {
    private String name;
    private String location;
    private String contact;
    private String date;
    private String person_no;

    public String getname() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPerson_no() {
        return person_no;
    }

    public void setPerson_no(String person_no) {
        this.person_no = person_no;
    }




}