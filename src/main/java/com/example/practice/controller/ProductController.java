package com.example.practice.controller;

import com.example.practice.dto.ProductRequest;
import com.example.practice.model.Product;
import com.example.practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);
        Product p = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){

        Product product = productService.getProductById(productId);

        if (product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId, productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(updateProduct);
    }
}
