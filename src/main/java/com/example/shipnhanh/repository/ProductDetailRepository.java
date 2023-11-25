package com.example.shipnhanh.repository;

import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsdetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends PagingAndSortingRepository<ProductsdetailEntity,Integer>{
    Page<ProductsdetailEntity> findAll(Pageable pageable);

    ProductsdetailEntity save(ProductsdetailEntity x);

    ProductsdetailEntity findById(Integer id);

    @Query(value = "select * from productsdetail  where name like N%?%",nativeQuery = true)
    Page<ProductsdetailEntity> findByName(String name,Pageable pageable);
    // query này t k dùng ô sửa cg v

    @Query(value = "select * from productsdetail  where idProduct=?",nativeQuery = true)
    Page<ProductsdetailEntity> findByProduct(String name,Pageable pageable);

    @Query(value = "SELECT new com.example.shipnhanh.DTO.ProductDetailDTO(p.name, m.nameMachanse, p.image, pd.price1, pd.price2, pd.status) FROM MerchantsEntity m " +
            "INNER JOIN ProductsdetailEntity pd ON m.id = pd.idMerchants " +
            "INNER JOIN ProductsEntity p ON p.id = pd.idProduct " +
            "WHERE  m.longitude = :longitude AND m.latitude = :latitude")
    List<ProductDetailDTO> findAll(@Param("longitude") Long longitude, @Param("latitude") Long latitude);

}
