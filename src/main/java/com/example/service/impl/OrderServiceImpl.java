package com.example.service.impl;

import com.example.dao.OrderDAO;
import com.example.dao.OrderDetailDAO;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhy on 11/20/16.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderDetailDAO orderDetailDAO;

    @Override
    public boolean createOrder(Order order, ArrayList<OrderDetail> orderDetailList) {
//        Order o=new Order();
//        o.setState(0);
//        o.setTime(new Timestamp(2131231231l));
//        o.setTotalPrice(44);
//        o.setUser_id(1);
        orderDAO.insertOrder(order);
//        ArrayList<OrderDetail> list= (ArrayList<OrderDetail>) orderDetailList;
//        System.err.println(orderDetailList.size());
//        for(OrderDetail orderDetail:orderDetailList) {
//            orderDetail.setOrder_id(order.getId());
//            orderDetailDAO.insertOrderDetail(orderDetail);
//        }
        orderDetailDAO.insertOrderDetailList(orderDetailList,order.getId());
        return true;
    }

    @Override
    public Order findById(int id) {
        return null;
    }
}
