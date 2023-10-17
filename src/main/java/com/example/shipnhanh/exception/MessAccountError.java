package com.example.shipnhanh.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessAccountError extends RuntimeException{
    private String message;
}
