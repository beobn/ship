package com.example.shipnhanh.DTO;


import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderDTO {
    private String id;
    private String nameOrder;
    private Double price;
    private String note;
    private String address;
    private Integer quantity;
    private String payerBy;
    private Double total;
    private Double feeMoney;
    private Double priceSale;
    private Long productId;
}
