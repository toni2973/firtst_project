package com.example.entity;


/**
 * Created by hhy on 11/19/16.
 */
public class Order {
    private int id;
    private int userid;
    private double totalPrice;
    private Long order_time;
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

    public void setState(int state) {this.state = state;}

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Long getOrder_time() {
        return order_time;
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

    public void setOrder_time(Long order_time) {
        this.order_time = order_time;
    }



}
