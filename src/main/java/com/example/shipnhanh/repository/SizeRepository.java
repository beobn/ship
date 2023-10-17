package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.SizeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SizeRepository extends PagingAndSortingRepository<SizeEntity,Integer> {

    Page<SizeEntity> findAll(Pageable pageable);
    SizeEntity findById(Integer id);
}
