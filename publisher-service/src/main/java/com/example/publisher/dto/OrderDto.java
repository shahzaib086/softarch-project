package com.example.publisher.dto;

import com.example.publisher.enums.OrderStatus;
import lombok.Data;


@Data
public class OrderDto {

    private Long id;

    private Long customerId;

    private OrderStatus status;

    private String orderDate;

    private Long totalPrice;

//    private List<OrderItemDto> orderItemDtoList;
}
