package com.example.shipnhanh.service;

import com.example.shipnhanh.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface OrderService {
    OrderEntity save(OrderEntity orderEntity,String username);
    Page<OrderEntity> findAll(Integer min , Integer max, LocalDate startDate, LocalDate endDate);
}
