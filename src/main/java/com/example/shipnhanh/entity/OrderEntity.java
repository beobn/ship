package com.example.shipnhanh.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "orders", schema = "shipnhanh", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name= "id")
    private  Long id;

    @Basic
    @Column(name = "note")
    private  String note;

    @Column(name = "create_date")
    private LocalDate creLocalDate;

    @Basic
    @Column(name = "total")
    private  Double total;

    @Basic
    @Column(name= "address")
    private  String address;

    @Basic
    @Column(name = "fee_money")
    private Double feeMoney;

    @Column(name = "user_id")
    private String userId;

    @Basic
    @Column(name= "payer_by")
    private String payerBy;

    @Basic
    @Column(name ="status")
    private int status;

    @Basic
    @Column(name = "numberPhone")
    private String numberPhone;
}
