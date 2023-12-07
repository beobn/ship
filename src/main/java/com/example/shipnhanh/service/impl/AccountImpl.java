package com.example.shipnhanh.service.impl;

import com.example.shipnhanh.entity.AccountEntity;
import com.example.shipnhanh.exception.MessAccountError;
import com.example.shipnhanh.exception.Validate;
import com.example.shipnhanh.repository.AccountRepository;
import com.example.shipnhanh.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountImpl implements AccountService {
    private final AccountRepository repository;
    private final Validate validate;

    public AccountImpl(AccountRepository repository, Validate validate) {
        this.repository = repository;
        this.validate = validate;
    }

    @Override
    public AccountEntity save(AccountEntity x) {
        if(checkDuplicates(validate.convertNumberPhone(x.getNumberphone()))){
            throw new MessAccountError("Số điện thoại đã tồn tại chọn quên mật khẩu hoặc đăng nhập");
        }
        return repository.save(x);
    }

    @Override
    public Optional<AccountEntity> Login(String phone, String pass) {

        if(checkDuplicates(validate.convertNumberPhone(phone))){
            Optional<AccountEntity> acc = repository.findByNumberphone(validate.convertNumberPhone(phone));
            if(!acc.get().getPassword().equals(validate.isValidateString(pass))){
                throw new MessAccountError("Số điện thoại hoặc mật khẩu không chính xác");
            }
            return acc;
        }else{
            throw new MessAccountError("Số điện thoại hoặc mật khẩu không chính xác");
        }

    }

    @Override
    public Page<AccountEntity> findAll(int pageNumber, int maxRecord) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<AccountEntity> page = repository.findAll(pageable);
        return page;
    }

    @Override
    public Page<AccountEntity> findByPhone(int pageNumber, int maxRecord, String phone) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<AccountEntity> page = repository.findByPhone(phone,pageable);
        return page;
    }

    @Override
    public void updateAcByNumberPhone(String token, String numberPhone) {
        repository.updateAccountByPhone (token,numberPhone);
    }

    private Boolean checkDuplicates(String phone){
        if(repository.findByNumberphone(phone)==null){
            return false;
        }
        return true;
    }
}
