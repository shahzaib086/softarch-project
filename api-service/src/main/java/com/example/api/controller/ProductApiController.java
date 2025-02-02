package com.example.api.controller;

import com.example.api.dto.ProductDto;
import com.example.api.service.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class ProductApiController {
    private final ProductServiceClient client;

    @Autowired
    public ProductApiController(ProductServiceClient client) {
        this.client = client;
    }

    @PostMapping("/api/product/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto product) {
        return client.createProduct(product);
    }
}
