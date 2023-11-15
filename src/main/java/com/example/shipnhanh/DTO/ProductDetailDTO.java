package com.example.shipnhanh.DTO;

import com.example.shipnhanh.entity.MerchantsEntity;
import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.entity.ProductsdetailEntity;
import lombok.*;
import org.hibernate.annotations.Cache;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetailDTO {
    private String name;
    private String nameMachanse;
    private String image;
    private BigDecimal price1;
    private BigDecimal price2;
    private Integer status;
}
