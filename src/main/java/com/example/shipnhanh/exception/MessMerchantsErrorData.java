package com.example.shipnhanh.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessMerchantsErrorData extends RuntimeException{
    private String message;
}
