package com.example.service.impl;

import com.example.dao.OrderDetailDAO;
import com.example.entity.OrderDetail;
import com.example.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hhy on 11/19/16.
 */
@Service("OrderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Override
    public List<OrderDetail> findOrderDetailByOrderId(int id) {
        return orderDetailDAO.findOrderDetailByOrderId(id);
    }
}
