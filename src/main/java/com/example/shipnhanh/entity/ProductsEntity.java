package com.example.shipnhanh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "shipnhanh", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "image")
    private String image;

    @Basic
    @Column(name= "count_seach")
    private int countSeach = 0;

    @Basic
    @Column(name = "time_seach")
    private LocalDate timeSeach;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity that = (ProductsEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(image, that.image) ;
    }

    @Override
    public String toString() {
        return "ProductsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", countSeach=" + countSeach +
                ", timeSeach=" + timeSeach +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image);
    }
}
