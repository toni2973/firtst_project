package com.example.controller;

import com.example.entity.Order;
import com.example.entity.OrderList;
import com.example.service.OrderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hhy on 11/19/16.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity createOrder(@RequestBody OrderList orderList) {
        int id = orderService.createOrder(orderList);

        JSONObject result=orderService.prePaidOrder(id);
        if (result.getString("ok")!=null) {
            result.remove("ok");
            return new ResponseEntity(result, HttpStatus.OK);
        }else
        {
            return new ResponseEntity(result, HttpStatus.EXPECTATION_FAILED);
        }

    }
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity getOrder(@RequestParam(value="id",required = true) int id) {
        Order order=orderService.findById(id);

        return new ResponseEntity(order,HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/get")
    public HttpEntity getOrderList(@RequestParam(value="id",required = true) int id) {

        JSONObject result=orderService.getOrder(id);

        return new ResponseEntity(result, HttpStatus.OK);


    }

    @RequestMapping(method = RequestMethod.POST,value = "/pay/{id}")
    public HttpEntity payOrder(@PathVariable("id")  int id) {

        JSONObject result=orderService.prePaidOrder(id);
        return new ResponseEntity("result",HttpStatus.CREATED);
    }
//    @RequestMapping(method = RequestMethod.POST,value = "/paid")
//    public HttpEntity paidOrder(@RequestBody JSONObject json) {
//
//        System.err.println(json.toString()+json.getInt("orderid"));
//        return new ResponseEntity("result",HttpStatus.CREATED);
//    }




}
