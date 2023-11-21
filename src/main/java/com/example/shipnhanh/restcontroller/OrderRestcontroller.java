package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.entity.OrderEntity;
import com.example.shipnhanh.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/order")
public class OrderRestcontroller {
    private  final OrderService orderService;

    public OrderRestcontroller(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderEntity> saveOrder (@RequestBody OrderEntity orderEntity) throws IOException{
        return   ResponseEntity.ok ().body (orderService.save (orderEntity));
    }
}
