package com.example.dao;

import com.example.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by hhy on 11/19/16.
 */
@Repository(value = "OrderDAO")
public interface OrderDAO {



    void insertOrder(Order order);


    Order findOrderById(@Param("id") int id);


    void deleteOrder(@Param("id") int id);


    void updateOrder(Order order);


    void paidOrder(@Param("id") int id );



}
