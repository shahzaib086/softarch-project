
package com.example.processor.service;

import com.example.processor.factory.PaymentServiceFactory;
import com.example.processor.model.ProcessedData;
import com.example.processor.model.dto.ProductDto;
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessorService {

    private final ProcessedDataRepository repository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final OrderPaymentRepository orderPaymentRepository;

    private final ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
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

    @KafkaListener(topics = "topic-update-product", groupId = "processor-group")
    public void updateProduct(String message) throws JsonProcessingException {

        log.info("UpdateProduct: Received message: {}", message);
        System.out.println(JsonUtil.toJson(message));

        // TODO other operation that makes sense
        ProductEntity product = JsonUtil.fromJson(message, ProductEntity.class);
        System.out.println("Before create after mapping");
        System.out.println(product);

        Optional<ProductEntity> prod =  productRepository.findById(product.getId());
        if (prod.isPresent()) {
            ProductEntity updateProd = prod.get();
            updateProd.setTitle(product.getTitle());
            updateProd.setCategory(product.getCategory());
            updateProd.setPrice(product.getPrice());
            updateProd.setDescription(product.getDescription());
            productRepository.save(updateProd);
        } else {
            throw new RuntimeException("Update failed no book found with this id: " + product.getId());
        }

        System.out.println("Product Update Successfully: " + message);
    }

    @KafkaListener(topics = "topic-delete-product", groupId = "processor-group")
    public void deleteProduct(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(message);
        JsonNode idNode = rootNode.get("id");

        if (idNode == null || idNode.isNull() || !idNode.canConvertToLong()) {
            log.error("Invalid ID received, cannot delete product.");
            return;
        }

        Long productId = idNode.asLong();
        productRepository.deleteById(productId);

        System.out.println("Product Delete Successfully: " + message);
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
