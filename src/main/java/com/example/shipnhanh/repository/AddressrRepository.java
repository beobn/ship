package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings ("ndks")
@Repository
public interface AddressrRepository extends JpaRepository<AddressEntity,Long> {
    @Override
    Optional<AddressEntity> findById(Long id);
    @Override
    List<AddressEntity> findAll();
}
