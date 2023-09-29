package com.example.practice.service.impl;

import com.example.practice.dao.ProductDao;
import com.example.practice.model.Product;
import com.example.practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
