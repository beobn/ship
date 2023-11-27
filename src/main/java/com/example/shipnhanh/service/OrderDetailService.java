package com.example.shipnhanh.service;

import com.example.shipnhanh.entity.OrderDetailEntity;

import java.util.List;

public interface OrderDetailService {
    public OrderDetailEntity saveOrderDetails(OrderDetailEntity  orderDetailEntity);

    public List<OrderDetailEntity>  getOrderDetails(Long idOrder);

}
