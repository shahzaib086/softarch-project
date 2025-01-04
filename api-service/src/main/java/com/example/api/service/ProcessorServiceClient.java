
package com.example.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// TODO extract hardcoded values into .properties files
@FeignClient(name = "processorServiceClient", url = "http://localhost:8080/simulation")
public interface ProcessorServiceClient {

    @PostMapping("/decode-and-send")
    ResponseEntity<String> decodeAndSend(@RequestBody Object routeRequest);
}


