package com.example.shipnhanh.Jwt;

import com.example.shipnhanh.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    JwtEntrypoint jwtEntrypoint;
    @Autowired
    JwtUtills jwtUtills;

    @Autowired
    JwtFiter jwtFiter;
    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder pe() {//Mã hóa password
        return new BCryptPasswordEncoder();
    }

    PasswordEncoder passwordEncoder() {//Mã hóa password
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManagerBean(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManager build = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder())
                .and().build();
        return build;
    }
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        //Disable crsf cho đường dẫn /rest/**
        http.csrf().disable ();

//        http.authorizeHttpRequests ()
//                .requestMatchers ("/admin/**", "/rest/guest/**","/api/auth/**").permitAll()//Phân quyền sử dụng
//                .requestMatchers("/order/**").authenticated()
//                .requestMatchers("/rest/admin/**","/admin/**","/admin/rest/**").hasRole("ADMIN")
//                .requestMatchers("/rest/staff/**").hasAnyRole("STAFF", "ADMIN")
//                .requestMatchers("/rest/user/**").hasAnyRole("USER", "STAFF", "ADMIN")
//                .and().exceptionHandling().authenticationEntryPoint(jwtEntrypoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFiter, UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/security/login/success")
                .defaultSuccessUrl("/security/login/success", false)

                .failureUrl("/security/login/error");
        http.rememberMe()
                .tokenValiditySeconds(86400);

        http.exceptionHandling()
                .accessDeniedPage("/security/unauthoried");

        http.logout()
                .logoutUrl("/security/logoff")// địa chỉ hệ thống xử lý
                .logoutSuccessUrl("/security/logoff/success");


        return http.build();
    }
}
