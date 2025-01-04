package com.example.api.controller;

import com.example.api.service.ProcessorServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {

    private  ProcessorServiceClient client;

    public ApiController(ProcessorServiceClient client) {
        this.client = client;
    }

    @GetMapping("/api/data")
    public ResponseEntity<String> fetchData(Object data) {
        return client.decodeAndSend(data);
    }
}
