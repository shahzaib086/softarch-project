package com.example.api.service;

import com.example.api.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "productServiceClient", url = "${processor.service.url}")
public interface ProductServiceClient {

    @GetMapping("/get-products")
    ResponseEntity<List<ProductDto>> getAllProducts();

    @PostMapping("/create-product")
    ResponseEntity<String> createProduct(@RequestBody ProductDto routeRequest);
}
