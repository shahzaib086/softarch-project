package com.example.processor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ORDER_PAYMENT")
public class OrderPaymentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDERـID")
    private Long orderId;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "EXPIRY")
    private String expiry;

    @Column(name = "CVV")
    private String cvv;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "CARD_PROVIDER")
    private String cardProvider;

}
