package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.entity.AccountEntity;
import com.example.shipnhanh.exception.Validate;
import com.example.shipnhanh.service.AccountService;
import com.example.shipnhanh.service.impl.AccountImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("rest/account")
@CrossOrigin("*")
public class AccountRestController {

    private final AccountImpl service;
    private final Validate validate;

    private final AccountService  accountService;

    public AccountRestController(AccountImpl service, Validate validate, AccountService accountService) {
        this.service = service;
        this.validate = validate;
        this.accountService = accountService;
    }

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

    @PostMapping("/login")
    public AccountEntity Login(
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String pass
    ){
        System.out.println ("login thành công");
        return accountService.Login(phone,pass);
    }

    @GetMapping("/register")
    public ResponseEntity<AccountEntity> Register(
            @RequestParam("phone") String phone,
            @RequestParam("pass") String pass
    ){
        AccountEntity account = new AccountEntity();
        account.setNumberphone(validate.convertNumberPhone(phone));
        account.setPassword(validate.isValidateString(pass));
        account.setPay(new BigDecimal(0));
        account.setRole(0);
        account.setCheckboom(0);
        service.save(account);
        return ResponseEntity.ok (account);
    }


}
