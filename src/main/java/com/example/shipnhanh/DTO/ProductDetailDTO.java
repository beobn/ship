package com.example.shipnhanh.DTO;

import com.example.shipnhanh.entity.MerchantsEntity;
import com.example.shipnhanh.entity.ProductsEntity;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetailDTO {
    private Long Id;
    private String name;
    private String nameMachanse;
    private String image;
    private BigDecimal price1;
    private BigDecimal price2;
    private Integer status;
    private Long merchants;
    private Long products;
    private Long longitude;
    private Long latitude;
}
