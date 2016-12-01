package com.example.dao;

import com.example.entity.OrderDetail;
import com.example.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhy on 11/19/16.
 */
@Repository(value = "OrderDetailDAO")
public interface OrderDetailDAO {



    void insertOrderDetail(OrderDetail orderDetail);


    UserEntity findOrderDetailById(@Param("id") int id);


    List<OrderDetail> findOrderDetailByOrderId(@Param("id") int id);



    void deleteOrderDetail(@Param("id") int id);


    void updateOrderDetail(OrderDetail OrderDetail);


    void insertOrderDetailList(@Param("orderDetailList") ArrayList<OrderDetail> orderDetailList, @Param("order_id")int order_id);


}
