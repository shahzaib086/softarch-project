package com.example.api.controller;

import com.example.api.dto.ProductDto;
import com.example.api.service.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class ProductApiController {
    private final ProductServiceClient client;

    @Autowired
    public ProductApiController(ProductServiceClient client) {
        this.client = client;
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return client.getAllProducts();
    }

    @PostMapping("/api/product/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto product) {
        return client.createProduct(product);
    }

    @PutMapping("/api/product/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDto product) {
        return client.updateProduct(product);
    }

    @PostMapping("/api/product/delete")
    public ResponseEntity<String> deleteProduct(@RequestBody ProductDto product) {
        return client.deleteProduct(product);
    }
}
