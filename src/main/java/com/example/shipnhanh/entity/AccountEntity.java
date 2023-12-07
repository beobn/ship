package com.example.shipnhanh.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "account", schema = "shipnhanh", catalog = "")
@Getter
@Setter
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "numberphone")
    private String numberphone;
    @Basic
    @Column(name = "pay")
    private BigDecimal pay;
    @Basic
    @Column(name = "role")
    private int role;
    @Basic
    @Column(name = "checkboom")
    private int checkboom;
    @Basic
    @Column(name = "tokens")
    private String token;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id && role == that.role && checkboom == that.checkboom && Objects.equals(password, that.password) && Objects.equals(numberphone, that.numberphone) && Objects.equals(pay, that.pay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, numberphone, pay, role, checkboom);
    }
}
