package com.example.publisher.controller;

import com.example.publisher.dto.DataRequest;
import com.example.publisher.dto.OrderDto;
import com.example.publisher.dto.OrderItemDto;
import com.example.publisher.dto.OrderPaymentDto;
import com.example.publisher.service.PublisherService;
import com.example.publisher.utils.RestHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/simulation")
@RequiredArgsConstructor
public class NavigationController {

    private final PublisherService publisherService;

    @PostMapping("/decode-and-send")
    public ResponseEntity<Map<String, String>> decodeAndSend(@RequestBody DataRequest routeRequest) {
        System.out.println(routeRequest);
        try {
            publisherService.processRouteRequest("example-topic",routeRequest);

            return ResponseEntity.ok(RestHelper.toJson("message", "Data sent to Kafka successfully."));
        } catch (Exception e) {
            log.error("decodeAndSend::", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    RestHelper.toJson("error","Error processing request: " + e.getMessage())
            );
        }
    }

    @PostMapping("/create-order")
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody OrderDto routeRequest) {
        System.out.println("CREATE ORDER");
        System.out.println(routeRequest);
        try {
            publisherService.processRouteRequest("topic-create-order",routeRequest);
            return ResponseEntity.ok(RestHelper.toJson("message", "Your order has been placed successfully."));
        } catch (Exception e) {
            log.error("createOrder::", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    RestHelper.toJson("error","Error processing request: " + e.getMessage())
            );
        }
    }

    @PostMapping("/cart-add")
    public ResponseEntity<Map<String, String>> cartAddItem(@RequestBody OrderItemDto routeRequest) {
        System.out.println("CART ADD ITEM");
        System.out.println(routeRequest);
        try {
            publisherService.processRouteRequest("topic-cart-add-item",routeRequest);
            return ResponseEntity.ok(RestHelper.toJson("message", "Item has been added to cart successfully."));
        } catch (Exception e) {
            log.error("createOrder::", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    RestHelper.toJson("error","Error processing request: " + e.getMessage())
            );
        }
    }

    @PostMapping("/order-payment")
    public ResponseEntity<Map<String, String>> orderPayment(@RequestBody OrderPaymentDto routeRequest) {
        System.out.println("ORDER PAYMENT");
        System.out.println(routeRequest);
        try {
            publisherService.processRouteRequest("topic-order-payment",routeRequest);
            return ResponseEntity.ok(RestHelper.toJson("message", "Your payment is in process, Please wait you will be notified."));
        } catch (Exception e) {
            log.error("createOrder::", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    RestHelper.toJson("error","Error processing request: " + e.getMessage())
            );
        }
    }

}
