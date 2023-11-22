package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.entity.OrderEntity;
import com.example.shipnhanh.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/admin/rest/order")
@CrossOrigin("*")
public class OrderRestcontroller {
    private  final OrderService orderService;

    public OrderRestcontroller(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderEntity> saveOrder (@RequestBody OrderEntity orderEntity) {
        return   ResponseEntity.ok ().body (orderService.save (orderEntity));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<OrderEntity>> getAllOrder(@PathVariable("page") Integer min, @RequestParam LocalDate startDate,@RequestParam LocalDate endDate){
        return  ResponseEntity.ok().body(orderService.findAll(min,
                20,startDate,endDate));
    }



}
