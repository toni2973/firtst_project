package com.example.service;

import com.example.entity.Order;
import com.example.entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhy on 11/19/16.
 */
public interface OrderService {

    boolean createOrder(Order order, ArrayList<OrderDetail> orderDetailList);

    Order findById(int id);

}
