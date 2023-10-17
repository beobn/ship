package com.example.shipnhanh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = "/admin")
    public String viewadmin(){
        return"admin/index.html";
    }

    @RequestMapping(value = "/view")
    public String demoview(){
        return"user/index.html";
    }

}
