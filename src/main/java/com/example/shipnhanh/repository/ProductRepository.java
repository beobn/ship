package com.example.shipnhanh.repository;

import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity,Integer> {
    ProductsEntity save(ProductsEntity x);

    @Query(value = "update products set image = null where image = ?", nativeQuery = true) // xóa ảnh
    public void deleteImage(String image);

    @Query("SELECT new com.example.shipnhanh.DTO.ProductDetailDTO(p.id, p.name, m.nameMachanse, p.image, pd.price1, pd.price2, pd.status, pd.idMerchants, pd.idProduct) FROM MerchantsEntity m " +
            "INNER JOIN ProductsdetailEntity pd ON m.id = pd.idMerchants " +
            "INNER JOIN ProductsEntity p ON p.id = pd.idProduct "  +
            "WHERE p.id = :id AND pd.idMerchants = :idMerchants")
    Optional<ProductDetailDTO> findByIdJoinProductDetail(@Param ("id") Long id, @Param("idMerchants") Long idMerchants);

    Optional<ProductsEntity> findByName(String nameProduct);

    @Query(value = "select p.name from products p  order by p.time_seach desc limit 5",nativeQuery = true)
    List<String> findProductRecently();

    @Query(value = "SELECT new com.example.shipnhanh.DTO.ProductDetailDTO(p.id, p.name, m.nameMachanse, p.image, pd.price1, " +
            "pd.price2, pd.status, p.countSeach, pd.idMerchants, pd.idProduct) FROM MerchantsEntity m " +
            "INNER JOIN ProductsdetailEntity pd ON m.id = pd.idMerchants " +
            "INNER JOIN ProductsEntity p ON p.id = pd.idProduct " +
            "WHERE CONCAT('%',p.name, '%')  LIKE %:nameProduct%")
    List<ProductDetailDTO> findByNameLike(@Param("nameProduct")String nameProduct);

    @Query(value = "SELECT new com.example.shipnhanh.DTO.ProductDetailDTO(p.id, p.name, m.nameMachanse, p.image," +
            " pd.price1, pd.price2, pd.status, pd.idMerchants, pd.idProduct) FROM MerchantsEntity m " +
            "LEFT JOIN ProductsdetailEntity pd ON m.id = pd.idMerchants " +
            "LEFT JOIN ProductsEntity p ON p.id = pd.idProduct " +
            "WHERE  m.id =:idMerchants")
    List<ProductDetailDTO> findByIdMatchanse (@Param ("idMerchants") Long idMerchants);


}
