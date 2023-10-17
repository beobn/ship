package com.example.shipnhanh.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "merchants", schema = "shipnhanh", catalog = "")
public class MerchantsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Time getTimeopen() {
        return timeopen;
    }

    public void setTimeopen(Time timeopen) {
        this.timeopen = timeopen;
    }

    public Time getTimeclose() {
        return timeclose;
    }

    public void setTimeclose(Time timeclose) {
        this.timeclose = timeclose;
    }

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
