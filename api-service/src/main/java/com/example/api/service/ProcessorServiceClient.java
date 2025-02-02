
package com.example.api.service;

import com.example.api.dto.DataRequest;
import com.example.api.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// TODO extract hardcoded values into .properties files
@FeignClient(name = "processorServiceClient", url = "${processor.service.url}")
public interface ProcessorServiceClient {

    @PostMapping("/decode-and-send")
    ResponseEntity<String> decodeAndSend(@RequestBody DataRequest routeRequest);

    @PostMapping("/create-order")
    ResponseEntity<String> createOrder(@RequestBody OrderDto routeRequest);
}


