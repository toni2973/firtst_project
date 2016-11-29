package com.example.dao;

import com.example.entity.Product;
import com.example.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hhy on 11/19/16.
 */
@Repository
public interface ProductDAO {
//    private int id;
//    private String title;
//    private double price;
//    private String description;
//    private String place;
//    private String pic;
//    private boolean freemail;
//    private String address;
    @Insert("INSERT INTO product(title,price,pic,description,place,freemail) VALUES(#{title},#{price},#{pic},#{description},#{place},#{freemail})")
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 将自动生成的主键重新设置到实体中，便于业务逻辑处理
    void insertProduct(Product product);

    @Select("SELECT * FROM product WHERE id = #{id}")
    @ResultType(Product.class)
    Product findProductById(@Param("id") int id);

    @Delete("delete from product where id=#{id}")
    void deleteProduct(@Param("id") int id);

    @Update( {"update product set name=#{title},price=#{price}",
            "where id=#{id}" })
    void updateProduct(Product product);

    @Select("SELECT * FROM product")
    @ResultType(List.class)
    List<Product> getAll();
}
