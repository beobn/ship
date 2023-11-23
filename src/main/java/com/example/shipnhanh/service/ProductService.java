package com.example.shipnhanh.service;

import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public ProductsEntity save(ProductsEntity x);

    public ProductsEntity findByID(Integer id);

    Page<ProductDetailDTO> findAllProduct(int pageNumber, int maxRecord, String nameProduct, Long longitude, Long latitude);
}
