
package com.example.api.service;

import com.example.api.dto.DataRequest;
import com.example.api.dto.OrderDto;
import com.example.api.dto.OrderItemDto;
import com.example.api.dto.OrderPaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
=======
>>>>>>> 84abe38fd478012d3ff0325acfa4d92cfa87f343


// TODO extract hardcoded values into .properties files
@FeignClient(name = "processorServiceClient", url = "${processor.service.url}")
public interface ProcessorServiceClient {

    @PostMapping("/decode-and-send")
    ResponseEntity<Map<String, String>> decodeAndSend(@RequestBody DataRequest routeRequest);

    @PostMapping("/create-order")
    ResponseEntity<Map<String, String>> createOrder(@RequestBody OrderDto routeRequest);

    @PostMapping("/cart-add")
    ResponseEntity<Map<String, String>> cartAddItem(@RequestBody OrderItemDto routeRequest);

    @PostMapping("/order-payment")
    ResponseEntity<Map<String, String>> orderPayment(@RequestBody OrderPaymentDto routeRequest);


}


