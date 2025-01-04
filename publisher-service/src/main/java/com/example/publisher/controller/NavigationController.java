package com.example.publisher.controller;

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
    public ResponseEntity<String> decodeAndSend(@RequestBody Object routeRequest) {
        try {
            publisherService.processRouteRequest(routeRequest);
            return ResponseEntity.ok("Data sent to Kafka successfully.");
        } catch (Exception e) {
            //TODO use a controller advice to handle exceptions
            log.error("decodeAndSend", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request: " + e.getMessage());
        }
    }
}
