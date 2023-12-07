package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.DTO.OrderDTO;
import com.example.shipnhanh.entity.OrderDetailEntity;
import com.example.shipnhanh.entity.OrderEntity;
import com.example.shipnhanh.exception.MessageSaveOrder;
import com.example.shipnhanh.service.OrderDetailService;
import com.example.shipnhanh.service.OrderService;
import com.example.shipnhanh.utills.CallAPIMBank;
import com.example.shipnhanh.utills.DistanceCalculator;
import com.example.shipnhanh.utills.UserNameLogin;
import jakarta.persistence.criteria.Order;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<OrderEntity> saveOrder (@ModelAttribute @Valid  OrderDTO orderDTO) {
        try {
            OrderEntity savedOrder = orderService.save(orderDTO);
            MessageSaveOrder messageSaveOrder = new MessageSaveOrder ();
            messageSaveOrder.setMessage ("Order saved successfully!");
            messageSaveOrder.setOrder(savedOrder);
            return ResponseEntity.ok().body(savedOrder);
        } catch (Exception e) {
            e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get-order")  // lấy hết đơn hàng theo người dùng
    public ResponseEntity<List<OrderEntity>> showHistoryOrder(){
        orderEntityList = orderService.findAll(UserNameLogin.getUserName ());
        System.out.println("user login " + UserNameLogin.getUserName());
         if(orderEntityList.size ()> 0){
             return  ResponseEntity.ok().body(orderEntityList);
         }
        return  ResponseEntity.badRequest ().build ();
    }

    @GetMapping("/payment/atm-banking")
    public ResponseEntity<Void> paymentOrderByUser (){
        System.out.println ("show log api");
        DistanceCalculator.getNumberKm ();
        return ResponseEntity.ok ().build();
    }

     @GetMapping("/get-order-details")
     public ResponseEntity<List<OrderDetailEntity>> getOrderDetails(
             @RequestParam(required = false) Long idOrder
     ){
        if(idOrder == null){
            return  ResponseEntity.badRequest ().build ();
        }
        return  ResponseEntity.ok (orderDetailService.getOrderDetails (idOrder));
     }



}
