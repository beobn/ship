package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.MerchantsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MerchantsRepository extends PagingAndSortingRepository<MerchantsEntity,Integer> {
    Page<MerchantsEntity> findAll(Pageable pageable);

    MerchantsEntity save(MerchantsEntity x);

    MerchantsEntity findById(Integer id);

    @Query(value = "select * from merchants where name like %?%",nativeQuery = true)
    Page<MerchantsEntity> findByName(String name,Pageable pageable);

}
