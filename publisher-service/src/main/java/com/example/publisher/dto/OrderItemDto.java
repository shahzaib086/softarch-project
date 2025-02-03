package com.example.publisher.dto;

import lombok.Data;

@Data
public class OrderItemDto {

    private Long id;

    private Long cartId;

    private Long productId;

    private Long quantity;

    private Long price;


}
