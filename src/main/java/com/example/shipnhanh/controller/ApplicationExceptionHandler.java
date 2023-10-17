package com.example.shipnhanh.controller;

import com.example.shipnhanh.constant.HttpConstantMessage;
import com.example.shipnhanh.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    //MERCHANTS_ERROR_DATA lỗi cửa hàng
    @ExceptionHandler(value = {
            MessMerchantsErrorData.class
    })
    protected ResponseEntity<ErrorMessage> MerchantsErrorData(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorMessage.builder().code(HttpConstantMessage.MERCHANTS_ERROR_DATA).message(exception.getMessage()).build()
        );
    }

    //LATITUDE_ERROR lỗi vĩ độ
    @ExceptionHandler(value = {
            MessLatitudeError.class
    })
    protected ResponseEntity<ErrorMessage> LatitudeError(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorMessage.builder().code(HttpConstantMessage.LATITUDE_ERROR).message(exception.getMessage()).build()
        );
    }

    //LONGITUDE_ERROR lỗi kinh độ
    @ExceptionHandler(value = {
            MessLongitudeError.class
    })
    protected ResponseEntity<ErrorMessage> LongitudeError(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorMessage.builder().code(HttpConstantMessage.LONGITUDE_ERROR).message(exception.getMessage()).build()
        );
    }

    //NUMBERPHONE_ERROR lỗi số điện thoại
    @ExceptionHandler(value = {
            MessNumberPhoneError.class
    })
    protected ResponseEntity<ErrorMessage> NumberPhoneError(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorMessage.builder().code(HttpConstantMessage.NUMBERPHONE_ERROR).message(exception.getMessage()).build()
        );
    }

    //STRING_ERROR lỗi String
    @ExceptionHandler(value = {
            MessStringError.class
    })
    protected ResponseEntity<ErrorMessage> StringError(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorMessage.builder().code(HttpConstantMessage.STRING_ERROR).message(exception.getMessage()).build()
        );
    }

    //STRING_ERROR lỗi String
    @ExceptionHandler(value = {
            MessAccountError.class
    })
    protected ResponseEntity<ErrorMessage> AccountError(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorMessage.builder().code(HttpConstantMessage.ACCOUNT_ERROR).message(exception.getMessage()).build()
        );
    }



}
