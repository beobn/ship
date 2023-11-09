package com.example.shipnhanh.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "productsdetail", schema = "shipnhanh", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsdetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "idMerchants")
    private int idMerchants;
    @Basic
    @Column(name = "idProduct")
    private int idProduct;
//    @Basic
//    @Column(name = "idSize")
//    private int idSize;
    @Basic
    @Column(name = "price1")
    private BigDecimal price1;
    @Basic
    @Column(name = "price2")
    private BigDecimal price2;
    @Basic
    @Column(name = "status")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMerchants() {
        return idMerchants;
    }

    public void setIdMerchants(int idMerchants) {
        this.idMerchants = idMerchants;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public BigDecimal getPrice1() {
        return price1;
    }

    public void setPrice1(BigDecimal price1) {
        this.price1 = price1;
    }

    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal price2) {
        this.price2 = price2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsdetailEntity that = (ProductsdetailEntity) o;
        return id == that.id && idMerchants == that.idMerchants && idProduct == that.idProduct
                && status == that.status && Objects.equals(price1, that.price1) && Objects.equals(price2, that.price2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMerchants, idProduct, price1, price2, status);
    }
}
