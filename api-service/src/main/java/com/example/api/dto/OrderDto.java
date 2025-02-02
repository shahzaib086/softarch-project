package com.example.api.dto;

import com.example.api.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderDto {

    private Long id;

    private Long customerId;

    private OrderStatus status;

    private String orderDate;

    private Long totalPrice;

}
