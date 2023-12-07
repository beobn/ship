package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.entity.AccountEntity;
import com.example.shipnhanh.exception.Validate;
import com.example.shipnhanh.service.AccountService;
import com.example.shipnhanh.service.impl.AccountImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("rest/account")
@CrossOrigin("*")
public class AccountRestController {

    private final AccountImpl service;
    private final Validate validate;

    private final AccountService  accountService;

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Value ("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    public AccountRestController(AccountImpl service, Validate validate, AccountService accountService,
            AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.service = service;
        this.validate = validate;
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
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
//    private Authentication authenticate(String username, String password) {
//        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken (username, password));
//    }

    private String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<> ();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date (System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String pass
    ){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(phone);
        final String token = generateToken(userDetails);
        AccountEntity account =  accountService.Login(phone,pass);
        if(!account.getNumberphone().isEmpty()){
            account.setToken(token);
            accountService.save(account);
            System.out.println("Luu thanh cong " + token);
        }
        System.out.println ("login thành công "+ account.getNumberphone ());
        return ResponseEntity.ok ( token);
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
