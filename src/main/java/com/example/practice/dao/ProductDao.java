package com.example.practice.dao;

import com.example.practice.dto.ProductRequest;
import com.example.practice.model.Product;

import java.util.List;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
