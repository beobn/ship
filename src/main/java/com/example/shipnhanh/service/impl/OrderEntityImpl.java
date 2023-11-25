package com.example.shipnhanh.service.impl;

import com.example.shipnhanh.entity.AccountEntity;
import com.example.shipnhanh.entity.OrderEntity;
import com.example.shipnhanh.repository.AccountRepository;
import com.example.shipnhanh.repository.OrderDetailRepository;
import com.example.shipnhanh.repository.OrderRepository;
import com.example.shipnhanh.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.time.LocalDate;

@Service
public class OrderEntityImpl implements OrderService {
    private  final OrderRepository orderRepository;
    private  final OrderDetailRepository orderDetailRepository;

    private  final AccountRepository accountRepository;


    public OrderEntityImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, AccountRepository accountRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public OrderEntity save(OrderEntity orderEntity,String username) {
        orderEntity.setCreLocalDate (LocalDate.now ());
        orderEntity.setStatus (0);
        return orderRepository.save (orderEntity);
    }

    @Override
    public Page<OrderEntity> findAll(Integer min, Integer max, LocalDate startDate, LocalDate endDate) {
        Pageable pageable = PageRequest.of(min, max);
        Page<OrderEntity> page = orderRepository.findAll(pageable,startDate,endDate);
        return new PageImpl<>(page.getContent(), pageable, page.getTotalElements());
    }

}
