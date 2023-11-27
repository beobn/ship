package com.example.shipnhanh.service;

import com.example.shipnhanh.DTO.OrderDTO;
import com.example.shipnhanh.entity.OrderEntity;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderEntity save(OrderDTO orderDTO);
    List<OrderEntity> findAll(String numberPhone);
}
