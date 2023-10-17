package com.example.shipnhanh.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "account", schema = "shipnhanh", catalog = "")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getCheckboom() {
        return checkboom;
    }

    public void setCheckboom(int checkboom) {
        this.checkboom = checkboom;
    }

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
