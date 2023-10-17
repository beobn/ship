package com.example.shipnhanh.service;

import com.example.shipnhanh.entity.AccountEntity;
import org.springframework.data.domain.Page;

public interface AccountService {
    public AccountEntity save(AccountEntity x);

    public AccountEntity Login(String phone,String pass);
    Page<AccountEntity> findAll(int pageNumber, int maxRecord);

    Page<AccountEntity> findByPhone(int pageNumber, int maxRecord, String phone);
}
