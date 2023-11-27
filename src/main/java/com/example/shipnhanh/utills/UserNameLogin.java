package com.example.shipnhanh.utills;

import org.springframework.security.core.context.SecurityContextHolder;
public class UserNameLogin {
        public UserNameLogin() {

        }

        public static String getUserName(){
            String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            String[] objects = principal.split(",",0);
            String numberPhone = objects[0].replace("org.springframework.security.core.userdetails.User","")
                    .replace("[","").replace("Username=","").trim();
            System.out.println(numberPhone);
            System.out.println("tai khoan login: "+numberPhone);
            return numberPhone;
        }


}
