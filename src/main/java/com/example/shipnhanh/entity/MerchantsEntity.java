package com.example.shipnhanh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "merchants", schema = "shipnhanh", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MerchantsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "longitude")
    private double longitude;
    @Basic
    @Column(name = "latitude")
    private double latitude;
    @Basic
    @Column(name = "numberphone")
    private String numberphone;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "timeopen")
    private Time timeopen;
    @Basic
    @Column(name = "timeclose")
    private Time timeclose;

    @Column(name = "product_id")
    private Long productId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantsEntity that = (MerchantsEntity) o;
        return id == that.id && Double.compare(that.longitude, longitude) == 0 && Double.compare(that.latitude, latitude) == 0 && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(numberphone, that.numberphone) && Objects.equals(image, that.image) && Objects.equals(timeopen, that.timeopen) && Objects.equals(timeclose, that.timeclose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, longitude, latitude, numberphone, image, timeopen, timeclose);
    }

    @Override
    public String toString() {
        return "MerchantsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", numberphone='" + numberphone + '\'' +
                ", image='" + image + '\'' +
                ", timeopen=" + timeopen +
                ", timeclose=" + timeclose +
                '}';
    }

}
