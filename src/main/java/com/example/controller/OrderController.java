package com.example.controller;

import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.OrderList;
import com.example.entity.UserEntity;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hhy on 11/19/16.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity createOrder(@RequestBody OrderList orderList) {
//      public HttpEntity createOrder(@RequestBody Map<String,Object> orderMap) {
//
//        Order order= (Order) orderMap.get("order");
//        List<OrderDetail> orderDetailList= (List<OrderDetail>) orderMap.get("orderDetailList");

        boolean created = orderService.createOrder(orderList.getOrder(),orderList.getOrderDetailList());
        if (!created) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
