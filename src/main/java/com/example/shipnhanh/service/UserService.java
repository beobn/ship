package com.example.shipnhanh.service;

import com.example.shipnhanh.entity.AccountEntity;
import com.example.shipnhanh.repository.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
public class UserService implements UserDetailsService {

    private final  AccountRepository accountRepository;

    public UserService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String numberPhone) throws UsernameNotFoundException {
        Optional<AccountEntity> account = accountRepository.findByNumberphone(numberPhone);
        if (account != null && account.get ().getNumberphone ().equals(numberPhone)){
            return User.withUsername(numberPhone).password(account.get ().getPassword()).roles(String.valueOf (account.get ().getRole())).build();
        }
        throw new UsernameNotFoundException("User not found with the name " + numberPhone);
    }
}
