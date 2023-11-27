package com.example.shipnhanh.service.impl;

import com.example.shipnhanh.entity.OrderDetailEntity;
import com.example.shipnhanh.repository.OrderDetailRepository;
import com.example.shipnhanh.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailService {

    private  final OrderDetailRepository orderDetailRepository;

    private  final List<OrderDetailEntity> orderDetailEntityList;

    public OrderDetailsServiceImpl(OrderDetailRepository orderDetailRepository, List<OrderDetailEntity> orderDetailEntityList) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailEntityList = orderDetailEntityList;
    }

    @Override
    public OrderDetailEntity saveOrderDetails(OrderDetailEntity orderDetailEntity) {
        return orderDetailRepository.save (orderDetailEntity);
    }

    @Override
    public List<OrderDetailEntity> getOrderDetails(Long idOrder) {
        return orderDetailRepository.findOrderDetailEntitiesByOrderId (idOrder);
    }
}
