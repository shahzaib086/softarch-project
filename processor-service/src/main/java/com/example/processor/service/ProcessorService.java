
package com.example.processor.service;

import com.example.processor.mapper.OrderMapper;
import com.example.processor.model.ProcessedData;
import com.example.processor.model.dto.OrderDto;
import com.example.processor.model.entity.OrderEntity;
import com.example.processor.model.enums.OrderStatus;
import com.example.processor.repository.OrderRepository;
import com.example.processor.repository.ProcessedDataRepository;
import com.example.processor.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessorService {

    private final ProcessedDataRepository repository;

    private final OrderRepository orderRepository;


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

}
