package com.example.processor.service;

import com.example.publisher.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class NotificationServiceClient {
    private final static long BASE_DELAY = 5000; // Base delay in milliseconds

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
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
//        kafkaTemplate.send("example-topic", data.toString(), message);
        kafkaTemplate.send(topic, data.toString(), message);
    }
}
