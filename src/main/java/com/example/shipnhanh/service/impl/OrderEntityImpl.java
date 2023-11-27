package com.example.shipnhanh.service.impl;

import com.example.shipnhanh.DTO.OrderDTO;
import com.example.shipnhanh.entity.AccountEntity;
import com.example.shipnhanh.entity.OrderDetailEntity;
import com.example.shipnhanh.entity.OrderEntity;
import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.repository.AccountRepository;
import com.example.shipnhanh.repository.OrderDetailRepository;
import com.example.shipnhanh.repository.OrderRepository;
import com.example.shipnhanh.repository.ProductRepository;
import com.example.shipnhanh.service.OrderService;
import com.example.shipnhanh.service.UserService;
import com.example.shipnhanh.utills.UserDetailsService;
import com.example.shipnhanh.utills.UserNameLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderEntityImpl implements OrderService {
    private  final OrderRepository orderRepository;
    private  final OrderDetailRepository orderDetailRepository;

    private  final AccountRepository accountRepository;

    private  final ProductRepository productRepository;
    private   OrderEntity orderEntity = null;

    public OrderEntityImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, AccountRepository accountRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderEntity save(OrderDTO orderDTO) {
        orderEntity = new OrderEntity ();
        String userName =  accountRepository.findNumberPhoneByUserLog (UserNameLogin.getUserName ());
        orderEntity.setCreLocalDate (LocalDate.now ());
        orderEntity.setUserId (userName);
        orderEntity.setNote (orderDTO.getNote ());
        orderEntity.setAddress (orderDTO.getAddress ());
        orderEntity.setTotal (orderDTO.getTotal ());
        orderEntity.setPayerBy (userName);
        orderEntity.setFeeMoney (orderDTO.getFeeMoney ());
        orderEntity.setStatus (0);
        orderRepository.save (orderEntity);
        if(orderEntity.getId () != null){
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity ();
            orderDetailEntity.setPrice (orderDTO.getPrice ());
            orderDetailEntity.setOrderId (orderEntity.getId ());
            orderDetailEntity.setProduct_id (orderDTO.getProductId ());
            orderDetailEntity.setStatus (0);
            orderDetailRepository.save (orderDetailEntity);
        }
        return orderEntity;
    }

    @Override
    public List<OrderEntity> findAll(String numberPhone) {
        List<OrderEntity> listOrder = orderRepository.findAll (numberPhone);
        return listOrder;
    }

}
