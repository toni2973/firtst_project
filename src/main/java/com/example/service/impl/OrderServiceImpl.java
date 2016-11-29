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
        order.setOrder_time(System.currentTimeMillis()/1000);
        order.setState(1);
        orderDAO.insertOrder(order);
        OrderDetail orderDetail=new OrderDetail();

        orderDetail.setOrder_id(order.getId());
        orderDetail.setProduct_id(orderList.getProductid());
        orderDetail.setQuantity(orderList.getNum());
        Product product=productDAO.findProductById(orderList.getProductid());

        orderDetail.setPic(product.getPic());
        orderDetail.setPrice(product.getPrice());
        orderDetail.setTitle(product.getTitle());
        System.out.println(product.getTitle());
        orderDetailDAO.insertOrderDetail(orderDetail);
        return order.getId();

    }

    @Override
    public JSONObject prePaidOrder(int id) {

        orderDAO.paidOrder(id);

        String url="http://hya.s1.natapp.cc/api/orders/pay/wexinnotify/test.do";
        Order order=findById(id);
        UserEntity user=userDAO.findById(order.getUserid());

        JSONObject json= null;
        try {
            json = CommonUtil.getxml( id ,order.getTotalPrice(), url,user.getOpenid(),"test",order.getOrder_time());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(json);
        json.put("orderid",id);

        return json;
    }

    @Override
    public JSONObject getOrder(int id) {
        Order order=orderDAO.findOrderById(id);
        List<OrderDetail> orderDetailList=orderDetailDAO.findOrderDetailByOrderId(id);
//        List<Map> odList=new ArrayList<>();
        JSONObject json=new JSONObject();
        order.setOrder_time(order.getOrder_time()*1000);
        json.put("order",order);
//        for (OrderDetail orderDetail:orderDetailList){
//            Map<String ,String >od=new HashMap<>();
//            od.put("totalPrice",orderDetail.get);
//        }
        json.put("orderDetailList",orderDetailList);
        UserEntity user=userDAO.findById(order.getUserid());
        JSONObject userJSON=new JSONObject();
        userJSON.put("nickname",user.getNickname());
        json.put("user",userJSON);

        return json;


    }

    private String getProduct(int id){
        List<OrderDetail> orderlist=orderDetailDAO.findOrderDetailByOrderId(id);
        String result="";
        for(OrderDetail orderDetail:orderlist){
            result=result+productDAO.findProductById(orderDetail.getId()).getTitle()+" ";
            if (result.length()>120) return result;

        }
        return result;
    }


}
