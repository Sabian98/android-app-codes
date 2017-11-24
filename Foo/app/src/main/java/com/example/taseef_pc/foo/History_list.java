package com.example.taseef_pc.foo;

import java.io.Serializable;

/**
 * Created by Taseef-PC on 2/9/2017.
 */

public class History_list implements Serializable {

    private String name;
    private String location;
    private String contact;
    private String date;


    public String gethname() {
        return name;
    }

    public void sethname(String name) {
        this.name = name;
    }

    public String gethLocation() {
        return location;
    }

    public void sethLocation(String location) {
        this.location = location;
    }

    public String gethContact() {
        return contact;
    }

    public void sethcontact(String contact) {
        this.contact = contact;
    }

    public String gethDate() {
        return date;
    }

    public void sethDate(String date) {
        this.date = date;
    }



}
