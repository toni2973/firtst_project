package com.example.service;

import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.OrderList;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhy on 11/19/16.
 */
public interface OrderService {



    Order findById(int id);

    int createOrder(OrderList orderList);

    JSONObject prePaidOrder(int id);


}
