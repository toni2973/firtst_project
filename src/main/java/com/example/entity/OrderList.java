package com.example.entity;

import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhy on 11/20/16.
 */
public class OrderList {
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(ArrayList<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    private ArrayList<OrderDetail> orderDetailList;
}
