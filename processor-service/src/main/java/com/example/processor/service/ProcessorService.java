
package com.example.processor.service;

import com.example.processor.model.ProcessedData;
import com.example.processor.model.dto.OrderDto;
import com.example.processor.model.entity.OrderEntity;
import com.example.processor.model.enums.OrderStatus;
import com.example.processor.repository.OrderRepository;
import com.example.processor.repository.ProcessedDataRepository;
import com.example.processor.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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
    public void createOrder(String message) {

        log.info("CreateOrder: Received message: {}", message);
        System.out.println(JsonUtil.toJson(message));

        // TODO other operation that makes sense
        OrderEntity order = new OrderEntity();
        order.setId(11L);
        order.setOrderDate(new Date());
        order.setCustomerId(22L);
        order.setStatus(OrderStatus.PROCESSING);
        order.setTotalPrice(100L);
        orderRepository.save(order);

        System.out.println("Order Saved Successfully: " + message);
    }

}
