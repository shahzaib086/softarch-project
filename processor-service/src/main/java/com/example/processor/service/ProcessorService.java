
package com.example.processor.service;

import com.example.processor.mapper.OrderMapper;
import com.example.processor.model.ProcessedData;
import com.example.processor.model.dto.OrderDto;
import com.example.processor.model.dto.ProductDto;
import com.example.processor.model.entity.OrderEntity;
import com.example.processor.model.entity.ProductEntity;
import com.example.processor.model.enums.OrderStatus;
import com.example.processor.repository.OrderRepository;
import com.example.processor.repository.ProcessedDataRepository;
import com.example.processor.repository.ProductRepository;
import com.example.processor.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessorService {

    private final ProcessedDataRepository repository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // TODO extract all hardcoded values into .properties files
    @KafkaListener(topics = "example-topic", groupId = "processor-group")
    public void processMessage(String message) {

        log.info("Received message: {}", message);

        ProcessedData data = new ProcessedData().setMessage(message);


        repository.save(data);
        System.out.println("Processed and saved message: " + message);
    }

    @KafkaListener(topics = "topic-create-order", groupId = "processor-group")
    public void createOrder(String message) throws JsonProcessingException {

        log.info("CreateOrder: Received message: {}", message);

        OrderEntity order = JsonUtil.fromJson(message, OrderEntity.class);
        order.setStatus(OrderStatus.CREATED);

        System.out.println("Before create after mapping");
        System.out.println(order);
        
        orderRepository.save(order);

        System.out.println("Order Saved Successfully: " + message);
    }

    @KafkaListener(topics = "topic-create-product", groupId = "processor-group")
    public void createProduct(String message) throws JsonProcessingException {

        log.info("CreateProduct: Received message: {}", message);
        System.out.println(JsonUtil.toJson(message));

        // TODO other operation that makes sense
        ProductEntity product = JsonUtil.fromJson(message, ProductEntity.class);
        System.out.println("Before create after mapping");
        System.out.println(product);

        productRepository.save(product);

        System.out.println("Product Created Successfully: " + message);
    }

//    @KafkaListener(topics = "topic-get-products", groupId = "processor-group")
//    public void getAllProducts(String message, String responseTopic) throws JsonProcessingException {
//        System.out.println("BILAL RASHID: " + message);
//
//        List<ProductDto> products = new ArrayList<>();
//        ProductDto productDto = new ProductDto();
//        productDto.setId(123L);
//        productDto.setTitle("Title");
//        productDto.setCategory("Category");
//        productDto.setDescription("Description");
//        productDto.setPrice(456L);
//        products.add(productDto);
//        products.add(productDto);
//        products.add(productDto);
//        products.add(productDto);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String productsJson = objectMapper.writeValueAsString(products);
//
//        // Send the products to the Kafka topic
//        kafkaTemplate.send(responseTopic, productsJson);
//
//        System.out.println("Product Created Successfully: " + message);
//    }

}
