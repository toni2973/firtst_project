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
@Repository
public interface OrderDetailDAO {

    @Insert("INSERT INTO orderDetail(product_id,quantity,order_id,pic,price,title)" +
            " VALUES(#{product_id},#{quantity},#{order_id},#{pic},#{price},#{title})")
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 将自动生成的主键重新设置到实体中，便于业务逻辑处理
    void insertOrderDetail(OrderDetail orderDetail);

    @Select("SELECT * FROM orderDetail WHERE id = #{id}")
    @ResultType(OrderDetail.class)
    UserEntity findOrderDetailById(@Param("id") int id);

    @Select("SELECT * FROM orderDetail WHERE order_id = #{id}")
    @ResultType(List.class)
    List<OrderDetail> findOrderDetailByOrderId(@Param("id") int id);


    @Delete("delete from orderDetail where id=#{id}")
    void deleteOrderDetail(@Param("id") int id);

    @Update( {"update orderDetail set product_id=#{product_id},quantity=#{quantity},pic=#{pic},title=#{title},price=#{price}",
            "where id=#{id}" })
    void updateOrderDetail(OrderDetail OrderDetail);

    @Select("<script>"+
            "insert into orderDetail(product_id, quantity, order_id,price,pic,title) "
            + "values"
            + "<foreach collection =\"orderDetailList\" item=\"item\" index= \"index\" separator =\",\"> "
            + "(#{item.product_id},#{item.quantity},#{order_id},#{item.price},#{item.pic}，#{item.title}) "
            + "</foreach> "
            + "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertOrderDetailList(@Param("orderDetailList") ArrayList<OrderDetail> orderDetailList, @Param("order_id")int order_id);


}
