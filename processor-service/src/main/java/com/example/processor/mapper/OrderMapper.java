package com.example.processor.mapper;

import com.example.processor.model.entity.OrderEntity;
import com.example.processor.model.enums.OrderStatus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public OrderEntity mapJsonToOrderEntity(String jsonMessage) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonMessage);

            OrderEntity order = new OrderEntity();
            order.setId(jsonNode.get("id").asLong());
            order.setCustomerId(jsonNode.get("customerId").asLong());
            order.setOrderDate(jsonNode.get("orderDate").asText().toUpperCase());
            order.setStatus(OrderStatus.valueOf(jsonNode.get("status").asText().toUpperCase()));
            order.setTotalPrice((long) jsonNode.get("totalPrice").asDouble());

            return order;
        } catch (Exception e) {
            throw new RuntimeException("Error mapping JSON to OrderEntity", e);
        }
    }
}
