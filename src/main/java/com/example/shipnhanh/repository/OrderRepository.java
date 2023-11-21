package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings ("nkfs")
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    Page<OrderEntity> findAll(Pageable pageable);
}
