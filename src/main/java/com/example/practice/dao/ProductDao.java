package com.example.practice.dao;

import com.example.practice.constant.ProductCategory;
import com.example.practice.dto.ProductRequest;
import com.example.practice.model.Product;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    List<Product> getProducts(ProductCategory category, String search);
}
