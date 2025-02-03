package com.example.processor.model.dto;

import com.example.processor.model.enums.OrderStatus;
import lombok.Data;

import java.util.List;


@Data
public class OrderDto {

    private Long id;

    private Long customerId;

    private OrderStatus status;

    private String orderDate;

    private Long totalPrice;

    private Long cartId;
}
