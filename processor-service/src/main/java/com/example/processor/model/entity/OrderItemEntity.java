package com.example.processor.model.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItemEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDERـID")
    private Long orderId;

    @Column(name = "PRODUCTـID")
    private Long productId;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "CART_ID")
    private Long cartId;

}
