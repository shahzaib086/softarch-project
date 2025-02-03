package com.example.processor.model.entity;

import com.example.processor.model.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
@Accessors(chain = true)
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "ORDER_STATUS")
    private OrderStatus status;

    @Column(name = "ORDER_DATE")
    private String orderDate;

    @Column(name = "TOTAL_PRICE")
    private Long totalPrice;

    @Column(name = "CART_ID")
    private Long cartId;

}
