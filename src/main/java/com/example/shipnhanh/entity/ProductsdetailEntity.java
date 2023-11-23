package com.example.shipnhanh.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
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
    private Long id;
    @Basic
    @Column(name = "idMerchants")
    private Long idMerchants;
    @Basic
    @Column(name = "idProduct")
    private Long idProduct;
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
