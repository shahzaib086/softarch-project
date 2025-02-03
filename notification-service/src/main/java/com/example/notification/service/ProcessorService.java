
package com.example.notification.service;

import com.example.notification.dto.OrderNotifyDto;
import com.example.notification.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessorService {

    @KafkaListener(topics = "order-payment", groupId = "notification-group")
    public void orderPaymentSuccess(String message) {
        try {
            log.info("Received message: {}", message);

            OrderNotifyDto entity = JsonUtil.fromJson(message, OrderNotifyDto.class);

            EmailNotificationService notificationService = EmailNotificationService.getInstance();

            notificationService.notify(entity.getReceiverId(), entity.getTitle(), entity.getMessage());

            log.info("Customer has been notified: {}", entity);
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
        }
    }

}
