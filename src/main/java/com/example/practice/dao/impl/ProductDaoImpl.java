package com.example.practice.dao.impl;

import com.example.practice.dao.ProductDao;
import com.example.practice.dto.ProductRequest;
import com.example.practice.model.Product;
import com.example.practice.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description," +
                " created_date, last_modified_date" +
                " FROM product WHERE product_id = :productId";

        Product product = new Product();

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        /*map.put("product_name", product.getProductName());
        map.put("category", product.getCategory());
        map.put("image_url", product.getImageUrl());
        map.put("price", product.getPrice());
        map.put("stock", product.getStock());
        map.put("description", product.getDescription());
        map.put("created_date", product.getCreatedDate());
        map.put("last_modified_date", product.getLast_modifiedDate());*/

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());


        if(productList.size() > 0){
            return productList.get(0);
        }else {
            return null;
        }


    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date date = new Date();
        map.put("createdDate", date);
        map.put("lastModifiedDate", date);

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return keyHolder.getKey().intValue();
    }


}
