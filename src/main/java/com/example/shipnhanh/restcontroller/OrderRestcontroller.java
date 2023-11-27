package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.DTO.OrderDTO;
import com.example.shipnhanh.entity.OrderEntity;
import com.example.shipnhanh.service.OrderDetailService;
import com.example.shipnhanh.service.OrderService;
import com.example.shipnhanh.utills.CallAPIMBank;
import com.example.shipnhanh.utills.UserNameLogin;
import jakarta.persistence.criteria.Order;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/rest/order")
@CrossOrigin("*")
public class OrderRestcontroller {
    private  final OrderService orderService;

    private final OrderDetailService orderDetailService;

    private List<OrderEntity> orderEntityList = null;

    public OrderRestcontroller(OrderService orderService, OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderEntity> saveOrder (@RequestBody(required = false) OrderDTO orderDTO) {
        return   ResponseEntity.ok ().body (orderService.save (orderDTO));
    }

    @GetMapping("/get-all/{page}")  // lấy hết đơn hàng theo người dùng
    public ResponseEntity<List<OrderEntity>> showHistoryOrder(){
         orderEntityList = orderService.findAll(UserNameLogin.getUserName ());
         if(orderEntityList.size ()> 0){
             return  ResponseEntity.ok().body(orderEntityList);
         }
        return  ResponseEntity.badRequest ().build ();
    }

//    @GetMapping("/payment/atm-banking")
//    public ResponseEntity<Void> paymentOrderByUser (){
//        System.out.println ("source log api");
//        callApiBank.contentApiBank ();
//        return ResponseEntity.ok ().build ();
//    }



}
