package com.example.shipnhanh.service.impl;


import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.exception.Validate;
import com.example.shipnhanh.repository.ProductRepository;
import com.example.shipnhanh.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductmImpl implements ProductService {

    private final ProductRepository repository;

    private final Validate validate;

    public ProductmImpl(ProductRepository repository, Validate validate) {
        this.repository = repository;
        this.validate = validate;
    }

    @Override
    public ProductsEntity save(ProductsEntity x) {
        return repository.save(vaLidate(x));
    }

    @Override
    public ProductsEntity findByID(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Page<ProductsEntity> findAll(int pageNumber, int maxRecord) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<ProductsEntity> page = repository.findAll(pageable);
        List<ProductsEntity> list = new ArrayList();
        for (int i=0;i<page.getContent().size();i++){
            list.add(page.getContent().get(i));
        }
        Page<ProductsEntity> pagedto = new PageImpl<>(list, pageable, page.getTotalElements());
        return pagedto;
    }

    @Override
    public Page<ProductsEntity> findByName(int pageNumber, int maxRecord, String name) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<ProductsEntity> page = repository.findByName(name,pageable);
        List<ProductsEntity> list = new ArrayList();
        for (int i=0;i<page.getContent().size();i++){
            list.add(page.getContent().get(i));
        }
        Page<ProductsEntity> pagedto = new PageImpl<>(list, pageable, page.getTotalElements());
        return pagedto;
    }

    private ProductsEntity vaLidate(ProductsEntity x){
        ProductsEntity y = new ProductsEntity();
        y.setId(x.getId());
        y.setImage(x.getImage());
        y.setName(validate.isValidateString(x.getName()));
        return y;
    }

}