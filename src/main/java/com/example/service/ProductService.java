package com.example.service;

import com.example.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hhy on 11/20/16.
 */

public interface ProductService {
    List<Product> getAll();

    Product findUserById(Integer id);
}
