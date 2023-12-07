package com.example.shipnhanh.service;

import com.example.shipnhanh.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountService {
    public AccountEntity save(AccountEntity x);

    public Optional<AccountEntity> Login(String phone, String pass);
    Page<AccountEntity> findAll(int pageNumber, int maxRecord);

    Page<AccountEntity> findByPhone(int pageNumber, int maxRecord, String phone);

    void updateAcByNumberPhone(String token, String numberPhone);
}
