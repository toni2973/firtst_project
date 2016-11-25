package com.example.service.impl;

import com.example.dao.OrderDAO;
import com.example.dao.OrderDetailDAO;
import com.example.dao.ProductDAO;
import com.example.dao.UserDAO;
import com.example.entity.*;
import com.example.service.OrderService;
import com.example.util.CommonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by hhy on 11/20/16.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    UserDAO userDAO;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderDetailDAO orderDetailDAO;
    @Autowired
    ProductDAO productDAO;
    @Override
    public Order findById(int id) {
        return orderDAO.findOrderById(id);
    }

    @Override
    public int createOrder(OrderList orderList) {
        Order order=new Order();
        order.setUserid(orderList.getUserid());
        order.setAddress(orderList.getAddress());
        order.setTotalPrice(orderList.getTotalPrice());
        order.setTime(new Timestamp(System.currentTimeMillis()));
        order.setState(1);
        orderDAO.insertOrder(order);
        OrderDetail orderDetail=new OrderDetail();

        orderDetail.setOrder_id(order.getId());
        orderDetail.setProduct_id(orderList.getProductid());
        orderDetail.setQuantity(orderList.getNum());
        orderDetailDAO.insertOrderDetail(orderDetail);
        return order.getId();

    }

    @Override
    public JSONObject prePaidOrder(int id) {

        orderDAO.paidOrder(id);

        String url="http://hya.s1.natapp.cc/orders/pay/wexinnotify/test.do";
        Order order=findById(id);
        UserEntity user=userDAO.findUserById(order.getUserid());

        JSONObject json= null;
        try {
            json = CommonUtil.getxml( id ,order.getTotalPrice(), url,user.getOpenid(),"test");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(json);

        return json;
    }
    private String getProduct(int id){
        List<OrderDetail> orderlist=orderDetailDAO.findOrderDetailByOrderId(id);
        String result="";
        for(OrderDetail orderDetail:orderlist){
            result=result+productDAO.findProductById(orderDetail.getId()).getTittle()+" ";
            if (result.length()>120) return result;

        }
        return result;
    }


}
