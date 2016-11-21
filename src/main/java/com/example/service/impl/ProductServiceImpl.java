package com.example.service.impl;

import com.example.dao.ProductDAO;
import com.example.entity.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hhy on 11/20/16.
 */
@Service("ProductService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;
    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }
}
