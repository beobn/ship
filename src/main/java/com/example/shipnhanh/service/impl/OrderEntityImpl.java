package com.example.shipnhanh.service.impl;

import com.example.shipnhanh.entity.OrderEntity;
import com.example.shipnhanh.repository.OrderRepository;
import com.example.shipnhanh.service.OrderService;
import org.springframework.stereotype.Service;

import java.io.Serial;

@Service
public class OrderEntityImpl implements OrderService {
    private  final OrderRepository orderRepository;

    public OrderEntityImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        orderEntity.setStatus (0);
        return orderRepository.save (orderEntity);
    }
}
