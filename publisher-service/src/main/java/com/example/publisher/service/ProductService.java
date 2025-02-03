package com.example.publisher.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.example.publisher.dto.ProductDto;
import com.example.publisher.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ProductService {
    private final static long BASE_DELAY = 5000; // Base delay in milliseconds

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private List<ProductDto> productsResponse = new ArrayList<>();

    public void processRouteRequest(String topic,Object data) {
        try {
            TimeUnit.MILLISECONDS.sleep(BASE_DELAY);
            log.info("Message {} sent", data);
            sendToKafka(topic, data);
        } catch (InterruptedException e) {
            log.error("Error during sleep", e);
            Thread.currentThread().interrupt();
        }
    }

    private void sendToKafka(String topic, Object data) {
        String message = JsonUtil.toJson(data);
        log.info("send data: " + message);
        System.out.println("Topic: " + topic);
        System.out.println("Send data: " + message);
        kafkaTemplate.send(topic, data.toString(), message);
    }

//    // Fetch products (send request to Kafka and await the response)
//    public List<ProductDto> fetchProducts(String requestTopic) throws InterruptedException {
//        log.info("Sending request to fetch products from topic: {}", requestTopic);
//
//        String responseTopic = "get-all-products-response";
//
//        // Send request to the Kafka topic
//        sendToKafka(requestTopic, responseTopic);
//
//        // Create a latch to wait for the response from the listener
//        CountDownLatch latch = new CountDownLatch(1);
//
//        synchronized (this) {
//            latch.await(5, TimeUnit.SECONDS); // Wait for response, adjust timeout if necessary
//        }
//
//        return productsResponse;
//    }
//
//    // Listener method for Kafka response topic
//    @KafkaListener(topics = "get-all-products-response", groupId = "processor-group")
//    public void listenForProductResponse(String message) {
//        log.info("Received response: {}", message);
//
//        // Deserialize the response message into a list of products
//        List<ProductDto> products = JsonUtil.fromJsonToList(message,  new TypeReference<List<ProductDto>>() {});
//
//        // Store the products in the response variable
//        synchronized (this) {
//            this.productsResponse = products;
//            notify();  // Notify the waiting thread that the response is available
//        }
//    }

    public List<ProductDto> fetchProducts(String topic) {
        log.info("Sending request to fetch products from topic: {}", topic);

        List<ProductDto> products = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setId(123L);
        productDto.setTitle("Title");
        productDto.setCategory("Category");
        productDto.setDescription("Description");
        productDto.setPrice(456L);
        products.add(productDto);
        products.add(productDto);
        products.add(productDto);
        products.add(productDto);

        return products; // Return the fetched products after the listener processes them
    }
}
