package com.example.shipnhanh.service;

import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsdetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ProductDetailService {
    public ProductsdetailEntity save(ProductsdetailEntity x);

    public ProductDetailDTO findByID(Integer id);
    Page<ProductDetailDTO> findAll(int pageNumber, int maxRecord);

    Page<ProductDetailDTO> findByName(int pageNumber, int maxRecord, String name);

}
