package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.OrderDetailEntity;
import jakarta.persistence.criteria.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings ("unused")
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long>  {

    List<OrderDetailEntity> findOrderDetailEntitiesByOrderId(Long  idOrder);
}
