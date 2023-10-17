package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.entity.AccountEntity;
import com.example.shipnhanh.exception.Validate;
import com.example.shipnhanh.service.impl.AccountImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("rest/account") @CrossOrigin("*")
public class AccountRestController {

    @Autowired
    AccountImpl service;
    @Autowired
    Validate validate;

    @GetMapping("/getall/{page}")
    public Page<AccountEntity> getALL(
            @PathVariable("page") Integer page,
            @RequestParam("seach") String seach
    ){
        if(seach.length()==0){
            return service.findAll(page,5);
        }else{
            return service.findByPhone(page,5,seach);
        }

    }

    @GetMapping("/login")
    public AccountEntity Login(
            @RequestParam("phone") String phone,
            @RequestParam("pass") String pass
    ){
        return service.Login(phone,pass);
    }

    @GetMapping("/register")
    public AccountEntity Register(
            @RequestParam("phone") String phone,
            @RequestParam("pass") String pass
    ){
        AccountEntity x = new AccountEntity();
        x.setNumberphone(validate.convertNumberPhone(phone));
        x.setPassword(validate.isValidateString(pass));
        x.setPay(new BigDecimal(0));
        x.setRole(0);
        x.setCheckboom(0);
        return service.save(x);
    }

}
