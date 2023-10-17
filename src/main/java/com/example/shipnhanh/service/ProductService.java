package com.example.shipnhanh.service;

import com.example.shipnhanh.entity.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface ProductService {
    public ProductsEntity save(ProductsEntity x);

    public ProductsEntity findByID(Integer id);
    Page<ProductsEntity> findAll(int pageNumber, int maxRecord);

    Page<ProductsEntity> findByName(int pageNumber, int maxRecord, String name);

}
