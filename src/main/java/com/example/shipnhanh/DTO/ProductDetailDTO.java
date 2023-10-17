package com.example.shipnhanh.DTO;

import com.example.shipnhanh.entity.MerchantsEntity;
import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.entity.SizeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
public class ProductDetailDTO {
    private int id;

    private MerchantsEntity merchants;
    private ProductsEntity product;
    private Integer size;
    private BigDecimal price1;
    private BigDecimal price2;
    private Integer status;


}
