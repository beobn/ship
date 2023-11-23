package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.entity.MerchantsEntity;
import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.service.impl.ProductmImpl;
import com.example.shipnhanh.service.impl.MerchantsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/view") @CrossOrigin("*")
public class ViewRestController {

    @Autowired
    ProductmImpl serviceProduct;

    @Autowired
    MerchantsImpl serviceMerchant;

//    @GetMapping("/getproduct/{page}")
//    public Page<ProductsEntity> getProduct(
//            @PathVariable("page") Integer page,
//            @RequestParam("seach") String seach
//    ){
//        if(seach.length()==0 || seach==null || seach.equals("undefined")){
//            return serviceProduct.findAll(0,6*(page+1));
//        }else{
//            return serviceProduct.findByName(0,6*(page+1),seach);
//        }
//
//    }

    @GetMapping("/getmerchant/{page}")
    public Page<MerchantsEntity> getMerchant(
            @PathVariable("page") Integer page,
            @RequestParam("seach") String seach
    ){
        if(seach.length()==0 || seach==null || seach.equals("undefined")){
            return serviceMerchant.findAll(0,4*(page+1));
        }else{
            return serviceMerchant.findByName(0,4*(page+1),seach);
        }

    }

}
