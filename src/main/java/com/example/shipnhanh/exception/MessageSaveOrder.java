package com.example.shipnhanh.exception;

import com.example.shipnhanh.entity.OrderEntity;
import lombok.Data;

@Data
public class MessageSaveOrder {
    private String message;
    private OrderEntity order;
}
