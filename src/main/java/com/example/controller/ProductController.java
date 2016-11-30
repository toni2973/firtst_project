package com.example.controller;

import com.example.entity.Product;
import com.example.service.ProductService;
import com.example.util.EhcacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hhy on 11/20/16.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    EhcacheUtil ehcacheUtil=EhcacheUtil.getInstance();
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity showAllProducts() {
        List<Product> productList= (List<Product>) ehcacheUtil.get("demo","productList");
        if (productList==null) {
            productList = productService.getAll();
            ehcacheUtil.put("demo","productList",productList);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public HttpEntity getProduct(@PathVariable("id") Integer id) {
        Product product= (Product) ehcacheUtil.get("demo","product"+id);
        if (product==null) {
            product = productService.findUserById(id);
            ehcacheUtil.put("demo","product"+id,product);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
