package com.example.shipnhanh.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "address", schema = "shipnhanh", catalog = "")
public class AddressEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "longitude")
    private double longitude;
    @Basic
    @Column(name = "latitude")
    private double latitude;
    @Basic
    @Column(name = "numberphone")
    private String numberphone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return id == that.id && Double.compare(that.longitude, longitude) == 0 && Double.compare(that.latitude, latitude) == 0 && Objects.equals(name, that.name) && Objects.equals(numberphone, that.numberphone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, longitude, latitude, numberphone);
    }
}
