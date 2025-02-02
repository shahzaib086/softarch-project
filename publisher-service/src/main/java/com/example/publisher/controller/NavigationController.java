package com.example.publisher.controller;

import com.example.publisher.dto.DataRequest;
import com.example.publisher.dto.OrderDto;
import com.example.publisher.dto.ProductDto;
import com.example.publisher.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/simulation")
@RequiredArgsConstructor
public class NavigationController {

    private final PublisherService publisherService;

    @PostMapping("/decode-and-send")
    public ResponseEntity<String> decodeAndSend(@RequestBody DataRequest routeRequest) {
        System.out.println("SHAHZAIB TEST");
        System.out.println(routeRequest);
        try {
            publisherService.processRouteRequest("example-topic",routeRequest);
            return ResponseEntity.ok("Data sent to Kafka successfully.");
        } catch (Exception e) {
            //TODO use a controller advice to handle exceptions
            log.error("decodeAndSend::", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request: " + e.getMessage());
        }
    }

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto routeRequest) {
        System.out.println("CREATE ORDER");
        System.out.println(routeRequest);
        try {
            publisherService.processRouteRequest("topic-create-order",routeRequest);
            return ResponseEntity.ok("Order Data sent to Kafka successfully.");
        } catch (Exception e) {
            log.error("createOrder::", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request: " + e.getMessage());
        }
    }

    @PostMapping("/create-product")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto routeRequest) {
        System.out.println("CREATE PRODUCT");
        System.out.println(routeRequest);
        try {
            publisherService.processRouteRequest("topic-create-product",routeRequest);
            return ResponseEntity.ok("Product Data sent to Kafka successfully.");
        } catch (Exception e) {
            log.error("createProduct::", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request: " + e.getMessage());
        }
    }

}
