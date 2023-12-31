package com.example.shipnhanh.service;

import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    public ProductsEntity save(ProductsEntity x);

    public Optional<ProductDetailDTO> findByID(Long id, Long idMerchants);

    List<ProductDetailDTO> findAllProduct(Double longitude, Double latitude);
}
