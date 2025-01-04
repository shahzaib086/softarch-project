package com.example.publisher.service;

import com.example.publisher.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PublisherService {
    private final static long BASE_DELAY = 50000; // Base delay in milliseconds

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void processRouteRequest(Object data) {
        try {
            TimeUnit.MILLISECONDS.sleep(BASE_DELAY);
            log.info("Message {} sent", data);
            sendToKafka(data);
        } catch (InterruptedException e) {
            log.error("Error during sleep", e);
            Thread.currentThread().interrupt();
        }
    }

    private void sendToKafka(Object data) {
        String message = JsonUtil.toJson(data);
        log.info("send data: " + message);
        System.out.println("send data: " + message);
        kafkaTemplate.send("navigation-data", data.toString(), message);
    }
}
