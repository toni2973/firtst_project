package com.example.entity;


import java.sql.Timestamp;


/**
 * Created by hhy on 11/19/16.
 */
public class Order {
    private int id;
    private int userid;
    private double totalPrice;
    private Timestamp time;
    private int state;
    private String address;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }



}
