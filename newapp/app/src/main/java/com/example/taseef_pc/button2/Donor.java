package com.example.taseef_pc.button2;

/**
 * Created by Taseef-PC on 2/8/2017.
 */


import java.io.Serializable;

/**
 * Created by XN on 1/31/2017.
 */

public class Donor implements Serializable {
    private String userName;
    private String location;
    private String sDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }
}