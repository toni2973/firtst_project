package com.example.entity;


import java.sql.Timestamp;


/**
 * Created by hhy on 11/19/16.
 */
public class Order {
    private int id;
    private int user_id;
    private double totalPrice;
    private Timestamp time;
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
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

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }



}
