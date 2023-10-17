package com.example.shipnhanh.service;


import com.example.shipnhanh.entity.MerchantsEntity;
import org.springframework.data.domain.Page;

public interface MerchantsService {
    public MerchantsEntity save(MerchantsEntity x);

    public MerchantsEntity findByID(Integer id);
    Page<MerchantsEntity> findAll(int pageNumber, int maxRecord);

    Page<MerchantsEntity> findByName(int pageNumber, int maxRecord, String name);
}
