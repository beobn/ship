package com.example.shipnhanh.exception;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorMessage {
    private String code;
    private String message;
}
