package com.example.shipnhanh.service;

import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {
    public ProductsEntity save(ProductsEntity x);

    public ProductsEntity findByID(Integer id);
    Page<ProductsEntity> findAll(int pageNumber, int maxRecord);

    Page<ProductsEntity> findByName(int pageNumber, int maxRecord, String nameProduct);

    Page<ProductDetailDTO> findAllProduct(int pageNumber, int maxRecord, String nameProduct, Long longitude, Long latitude);
}
