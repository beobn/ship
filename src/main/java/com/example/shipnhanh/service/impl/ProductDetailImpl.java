package com.example.shipnhanh.service.impl;



import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.entity.ProductsdetailEntity;
import com.example.shipnhanh.repository.*;
import com.example.shipnhanh.service.ProductDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailImpl implements ProductDetailService {

    private final ProductDetailRepository repository;

    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;

    private final MerchantsRepository merchantsRepository;

    public ProductDetailImpl(ProductDetailRepository repository, ProductRepository productRepository, SizeRepository sizeRepository, MerchantsRepository merchantsRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.merchantsRepository = merchantsRepository;
    }


    @Override
    public ProductsdetailEntity save(ProductsdetailEntity x) {
        return repository.save(x);
    }

    @Override
    public ProductDetailDTO findByID(Integer id) {
        return convertProductDetailDTO(repository.findById(id));
    }

    @Override
    public Page<ProductDetailDTO> findAll(int pageNumber, int maxRecord) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<ProductsdetailEntity> page = repository.findAll(pageable);
        List<ProductDetailDTO> list = new ArrayList();
        for (int i=0;i<page.getContent().size();i++){
            list.add(convertProductDetailDTO(page.getContent().get(i)));
        }
        Page<ProductDetailDTO> pagedto = new PageImpl<>(list, pageable, page.getTotalElements());
        return pagedto;
    }

    @Override
    public Page<ProductDetailDTO> findByName(int pageNumber, int maxRecord, String name) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<ProductsdetailEntity> page = repository.findByName(name,pageable);
        List<ProductDetailDTO> list = new ArrayList();
        for (int i=0;i<page.getContent().size();i++){
            list.add(convertProductDetailDTO(page.getContent().get(i)));
        }
        Page<ProductDetailDTO> pagedto = new PageImpl<>(list, pageable, page.getTotalElements());
        return pagedto;
    }
    private ProductDetailDTO convertProductDetailDTO(ProductsdetailEntity x){
        ProductDetailDTO prddt = new ProductDetailDTO();
        prddt.setId(x.getId());
        prddt.setPrice1(x.getPrice1());
        prddt.setPrice2(x.getPrice2());
        prddt.setStatus(x.getStatus());
        prddt.setProduct((productRepository.findById(x.getIdProduct())));
        prddt.setMerchants(merchantsRepository.findById(x.getIdMerchants()));
        prddt.setSize(x.getIdSize());
        return prddt;
    }


}
