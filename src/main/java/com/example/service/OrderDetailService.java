package com.example.service;

import com.example.entity.OrderDetail;


import java.util.List;

/**
 * Created by hhy on 11/19/16.
 */
public interface OrderDetailService {
    List<OrderDetail> findOrderDetailByOrderId(int id);

}
