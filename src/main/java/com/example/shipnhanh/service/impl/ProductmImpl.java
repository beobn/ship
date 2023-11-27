package com.example.shipnhanh.service.impl;


import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.exception.Validate;
import com.example.shipnhanh.repository.ProductDetailRepository;
import com.example.shipnhanh.repository.ProductRepository;
import com.example.shipnhanh.service.ProductService;
import com.fasterxml.jackson.databind.util.ClassUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductmImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductDetailRepository productDetailRepository;

    private final Validate validate;

    public ProductmImpl(ProductRepository repository, ProductDetailRepository productDetailRepository, Validate validate) {
        this.repository = repository;
        this.productDetailRepository = productDetailRepository;
        this.validate = validate;
    }

    @Override
    public ProductsEntity save(ProductsEntity x) {
        return repository.save(validate(x));
    }

    @Override
    public Optional<ProductDetailDTO> findByID(Long id) {
        if(id ==null) {
            validate.convertLongitude (Double.valueOf (id));
            return null;
        }
        return  repository.findByIdJoinProductDetail (id);
    }

    @Override
    public List<ProductDetailDTO> findAllProduct(Long longitude, Long latitude) {
        List<ProductDetailDTO> productDetailDTOList = productDetailRepository.findAll (longitude,latitude);
            System.out.println (productDetailDTOList.toString ());
            return productDetailDTOList;
    }


    private ProductsEntity validate(ProductsEntity x){
        ProductsEntity y = new ProductsEntity();
        y.setId(x.getId());
        y.setImage(x.getImage());
        y.setName(validate.isValidateString(x.getName()));
        return y;
    }

}
