package com.example.shipnhanh.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class MessNullException extends RuntimeException{
    private String message;
}
