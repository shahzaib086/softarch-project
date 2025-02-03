
package com.example.processor.service;

import com.example.processor.factory.PaymentServiceFactory;
import com.example.processor.model.ProcessedData;
import com.example.processor.model.dto.OrderNotifyDto;
import com.example.processor.model.entity.OrderEntity;
import com.example.processor.model.entity.OrderItemEntity;
import com.example.processor.model.entity.OrderPaymentEntity;
import com.example.processor.model.entity.ProductEntity;
import com.example.processor.model.enums.OrderStatus;
import com.example.processor.repository.OrderItemRepository;
import com.example.processor.repository.OrderPaymentRepository;
import com.example.processor.repository.OrderRepository;
import com.example.processor.repository.ProcessedDataRepository;
import com.example.processor.repository.ProductRepository;
import com.example.processor.utils.JsonUtil;
import com.example.publisher.service.PublisherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessorService {

    private final ProcessedDataRepository repository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final OrderPaymentRepository orderPaymentRepository;

    private final ProductRepository productRepository;

    private final NotificationServiceClient notificationServiceClient;

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
        System.out.println(message);

        orderRepository.save(order);

        System.out.println("Order Saved Successfully: " + message);
    }

    @KafkaListener(topics = "topic-cart-add-item", groupId = "processor-group")
    public void cartAddItem(String message) throws JsonProcessingException {

        log.info("cartAddItem: Received message: {}", message);

        OrderItemEntity orderItem = JsonUtil.fromJson(message, OrderItemEntity.class);

        System.out.println("Before create after mapping");
        System.out.println(message);

        orderItemRepository.save(orderItem);

        System.out.println("Order Saved Successfully: " + message);
    }

    @KafkaListener(topics = "topic-order-payment", groupId = "processor-group")
    public void orderPayment(String message) throws JsonProcessingException {
        try {
            log.info("orderPayment: Received message: {}", message);

            OrderPaymentEntity payment = JsonUtil.fromJson(message, OrderPaymentEntity.class);
            String cardNumber = payment.getCardNumber();
            String expiry = payment.getExpiry();
            String cvv = payment.getCvv();
            Long amount = payment.getAmount();

            PaymentService paymentService = PaymentServiceFactory.getPaymentService(payment.getCardProvider());
            Boolean checkPayment = paymentService.processPayment(cardNumber, expiry, cvv, amount);

            if( checkPayment ){
                orderPaymentRepository.save(payment);
                System.out.println("Your Payment has been completed Successfully." + message);

                OrderNotifyDto notify = new OrderNotifyDto();
                notify.setReceiverId(123L);
                notify.setTitle("Payment successful");
                notify.setMessage("Your payment has been completed Successfully.");
                notificationServiceClient.processRouteRequest("order-payment", notify);

            } else {
                System.out.println("Your Payment has been failed." + message);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
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

}
