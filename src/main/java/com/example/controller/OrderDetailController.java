package com.example.controller;

import com.example.entity.OrderDetail;
import com.example.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hhy on 11/19/16.
 */
@RestController
@RequestMapping("/orders")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(method = RequestMethod.GET,value="/{id}")
    public HttpEntity getOrderDetail(@PathVariable("id")Integer id){
        List<OrderDetail> loe=orderDetailService.findOrderDetailByOrderId(id);
        return new ResponseEntity<>(loe, HttpStatus.OK);

    }


}
