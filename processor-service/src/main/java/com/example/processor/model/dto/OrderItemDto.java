package com.example.processor.model.dto;

import lombok.Data;

@Data
public class OrderItemDto {

    private Long id;

    private Long productId;

    private Long quantity;

    private String description;

//    private OrderDto orderDto;
}
