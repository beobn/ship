package com.example.shipnhanh.service.impl;

import com.example.shipnhanh.entity.OrderDetailEntity;
import com.example.shipnhanh.repository.OrderDetailRepository;
import com.example.shipnhanh.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsServiceImpl implements OrderDetailService {

    private  final OrderDetailRepository orderDetailRepository;

    public OrderDetailsServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetailEntity saveOrderDetails(OrderDetailEntity orderDetailEntity) {
        return orderDetailRepository.save (orderDetailEntity);
    }
}
