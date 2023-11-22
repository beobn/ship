package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    @Query( "select o from OrderEntity o where o.creLocalDate between :startDate AND :endDate")
    Page<OrderEntity> findAll(Pageable pageable, @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
}
