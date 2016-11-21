package com.example.dao;

import com.example.entity.Order;
import com.example.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by hhy on 11/19/16.
 */
@Repository
public interface OrderDAO {

    @Insert("INSERT INTO orders(user_id,totalPrice,order_time,state)VALUES(#{user_id},#{totalPrice},#{time},#{state})")
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 将自动生成的主键重新设置到实体中，便于业务逻辑处理
    void insertOrder(Order order);



    @Select("SELECT * FROM orders WHERE id = #{id}")
    @ResultType(Order.class)
    UserEntity findOrderById(@Param("id") int id);

    @Delete("delete from orders where id=#{id}")
    void deleteOrder(@Param("id") int id);

    @Update( {"update orders set user_id=#{user_id},totalPrice=#{totalPrice},order_time=#{time},state=#{state}",
            "where id=#{id}" })
    void updateOrder(Order order);



}
