package com.example.shipnhanh.service.impl;


import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.exception.Validate;
import com.example.shipnhanh.repository.ProductDetailRepository;
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
        return repository.save(vaLidate(x));
    }

    @Override
    public ProductsEntity findByID(Integer id) {
        if(id ==null) {
            System.out.println ("id null");
            validate.convertLongitude (Double.valueOf (id));
            return null;
        }
        return repository.findById(id).get ();
    }

    @Override
    public Page<ProductsEntity> findAll(int pageNumber, int maxRecord) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<ProductsEntity> page = repository.findAll(pageable);
        List<ProductsEntity> list = new ArrayList();
        for (int i=0;i<page.getContent().size();i++){
            list.add(page.getContent().get(i));
        }
        Page<ProductsEntity> pagedto = new PageImpl<>(page.getContent (), pageable, page.getTotalElements());
        return pagedto;
    }

    @Override
    public Page<ProductsEntity> findByName(int pageNumber, int maxRecord, String nameProduct) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<ProductsEntity> page = repository.findByName(nameProduct,pageable);
        List<ProductsEntity> list = new ArrayList();
        for (int i=0;i<page.getContent().size();i++){
            list.add(page.getContent().get(i));
            Optional<ProductsEntity> productsEntity = Optional.ofNullable (page.getContent ().get (i));
            productsEntity.orElseThrow ().setCountSeach (i++);
            repository.save (productsEntity.get ());
        }
        Page<ProductsEntity> pagedto = new PageImpl<>(list, pageable, page.getTotalElements());
        return pagedto;
    }

    @Override
    public Page<ProductDetailDTO> findAllProduct(int pageNumber, int maxRecord, String nameProduct,Long longitude, Long latitude) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<ProductDetailDTO> page = productDetailRepository.findAll (nameProduct,pageable,longitude,latitude);
        List<ProductDetailDTO> list =  page.getContent ();
        for (int i=0;i< list.size();i++){
            Optional<ProductsEntity> productsEntity = repository.findByName (nameProduct);
            if(productsEntity.isPresent ()) {
                productsEntity.orElseThrow ().setCountSeach (i++);
                repository.save (productsEntity.get ());
            }
        }
        System.out.println (page.getContent ());
        System.out.println(list.toString ());
        Page<ProductDetailDTO> pagedto = new PageImpl<>(list, pageable, page.getTotalElements());
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
