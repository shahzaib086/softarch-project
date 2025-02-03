package com.example.processor.repository;

import com.example.processor.model.entity.OrderItemEntity;
import com.example.processor.model.entity.OrderPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPaymentEntity, Long> {
}
