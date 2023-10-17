package com.example.shipnhanh.restcontroller;


import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.service.impl.ProductmImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/rest/product") @CrossOrigin("*")
public class ProductRestController {
    @Autowired
    ProductmImpl service;

    @GetMapping("/getall/{page}")
    public Page<ProductsEntity> getALL(
            @PathVariable("page") Integer page,
            @RequestParam("seach") String seach
    ){
        if(seach.length()==0 || seach==null || seach.equals("undefined")){
            return service.findAll(page,5);
        }else{
            return service.findByName(page,5,seach);
        }

    }

    @PostMapping ("/save")
    public ProductsEntity save(@RequestBody ProductsEntity param){
        return service.save(param);
    }

    @GetMapping("/find_id")
    public ProductsEntity getname(@RequestParam("id") Integer id){
        return service.findByID(id);
    }
}
