package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.ProductsdetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
public interface ProductDetailRepository extends PagingAndSortingRepository<ProductsdetailEntity,Integer>{
    Page<ProductsdetailEntity> findAll(Pageable pageable);

    ProductsdetailEntity save(ProductsdetailEntity x);

    ProductsdetailEntity findById(Integer id);

    @Query(value = "select * from productsdetail  where name like %?%",nativeQuery = true)
    Page<ProductsdetailEntity> findByName(String name,Pageable pageable);

    @Query(value = "select * from productsdetail  where idProduct=?",nativeQuery = true)
    Page<ProductsdetailEntity> findByProduct(String name,Pageable pageable);
}
