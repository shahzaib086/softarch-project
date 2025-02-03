package com.example.api.service;

import com.example.api.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "productServiceClient", url = "${processor.service.url}")
public interface ProductServiceClient {
    @PostMapping("/create-product")
    ResponseEntity<String> createProduct(@RequestBody ProductDto routeRequest);
}
