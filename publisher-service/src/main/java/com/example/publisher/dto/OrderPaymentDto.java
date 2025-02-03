package com.example.publisher.dto;

import lombok.Data;

@Data
public class OrderPaymentDto {

    private Long id;

    private Long orderId;

    private String cardNumber;

    private String expiry;

    private String cvv;

    private Long amount;

    private String cardProvider;

}
