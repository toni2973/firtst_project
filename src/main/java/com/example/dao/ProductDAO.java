package com.example.dao;

import com.example.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hhy on 11/19/16.
 */
@Repository(value ="ProductDAO" )
public interface ProductDAO {


    void insertProduct(Product product);


    Product findProductById(@Param("id") int id);


    void deleteProduct(@Param("id") int id);


    void updateProduct(Product product);


    List<Product> getAll();
}
