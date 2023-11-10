package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductsEntity,Integer> {
    Page<ProductsEntity> findAll(Pageable pageable);

    ProductsEntity save(ProductsEntity x);

    @Query(value = "update products set image = null where image = ?", nativeQuery = true)
    public void deleteImage(String image);

    ProductsEntity findById(Integer id);

    @Query(value = "select * from products where name like %?%",nativeQuery = true)
    Page<ProductsEntity> findByName(String nameProduct,Pageable pageable);

    @Query(value = "select p.name from products p  order by p.time_seach desc limit 5",nativeQuery = true)
    List<String> findProductRecently();

    Optional<ProductsEntity> findByNameLike (String nameProduct);

}
