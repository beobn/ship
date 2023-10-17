package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<AccountEntity,Integer> {

    Page<AccountEntity> findAll(Pageable pageable);

    AccountEntity save(AccountEntity x);

    AccountEntity findByNumberphone(String number);

    @Query(value = "select * from Account where numberphone like %?%",nativeQuery = true)
    Page<AccountEntity> findByPhone(String name, Pageable pageable);
}
