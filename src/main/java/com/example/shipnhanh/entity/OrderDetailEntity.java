package com.example.shipnhanh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail", schema = "shipnhanh", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name= "id")
    private  Long id;
    @Basic
    @Column(name ="price")
    private Double price;
    @Column(name = "status")
    private  int status;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name ="product_id")
    private Long product_id;
    @Basic
    @Column(name = "price_sale")
    private Double priceSale;
    @Column(name = "sale_id")
    private Long saleId;
}
