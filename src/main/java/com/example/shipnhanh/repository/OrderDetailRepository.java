package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.OrderDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unsued")
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long>  {

}
