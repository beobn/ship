package com.example.shipnhanh.repository;

import com.example.shipnhanh.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends PagingAndSortingRepository<AccountEntity,Integer> {

    Page<AccountEntity> findAll(Pageable pageable);

    AccountEntity save(AccountEntity x);

    Optional<AccountEntity> findByNumberphone(String number);

    @Query(value = "select * from Account where numberphone like %?%",nativeQuery = true)
    Page<AccountEntity> findByPhone(String name, Pageable pageable);

    @Query(value = "SELECT a.numberphone FROM  AccountEntity  a where a.numberphone = :numberPhone")
    String findNumberPhoneByUserLog(String numberPhone);

    @Modifying
    @Transactional
    @Query(value = "update AccountEntity acc set acc.token = :token where acc.numberphone = :numberPhone")
    void updateAccountByPhone (@Param ("token")String token, @Param ("numberPhone") String numberPhone);
}
