package com.example.shipnhanh.service;


import com.example.shipnhanh.entity.SizeEntity;
import org.springframework.data.domain.Page;

public interface SizeService {
    Page<SizeEntity> findAll(int pageNumber, int maxRecord);
    public SizeEntity findByID(Integer id);
}
