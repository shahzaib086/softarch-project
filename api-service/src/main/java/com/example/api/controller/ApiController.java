package com.example.api.controller;

import com.example.api.dto.DataRequest;
import com.example.api.dto.OrderDto;
import com.example.api.dto.OrderItemDto;
import com.example.api.dto.OrderPaymentDto;
import com.example.api.service.ProcessorServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class ApiController {

    private final ProcessorServiceClient client;

    @Autowired
    public ApiController(ProcessorServiceClient client) {
        this.client = client;
    }


    @PostMapping("/api/data")
    public ResponseEntity<Map<String, String>> fetchData(@RequestBody DataRequest data) {
        return client.decodeAndSend(data);
    }

    @PostMapping("/api/order/checkout")
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody OrderDto data) {
        return client.createOrder(data);
    }

    @PostMapping("/api/cart/add")
    public ResponseEntity<Map<String, String>> cartAddItem(@RequestBody OrderItemDto data) {
        return client.cartAddItem(data);
    }

    @PostMapping("/api/order/payment")
    public ResponseEntity<Map<String, String>> cartAddItem(@RequestBody OrderPaymentDto data) {
        return client.orderPayment(data);
    }

}
