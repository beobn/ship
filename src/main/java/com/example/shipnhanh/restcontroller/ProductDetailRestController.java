package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsdetailEntity;
import com.example.shipnhanh.service.impl.ProductDetailImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("admin/rest/productdetail") @CrossOrigin("*")
public class ProductDetailRestController {
    private final ProductDetailImpl service;

    public ProductDetailRestController(ProductDetailImpl service) {
        this.service = service;
    }

    @GetMapping("/getall/{page}")
    public Page<ProductDetailDTO> getALL(
            @PathVariable("page") Integer page,
            @RequestParam("seach") String seach
    ){
        if(seach.isEmpty()){
            return service.findAll(page,5);
        }else{
            return service.findByName(page,5,seach);
        }
    }

    @PostMapping ("/save")
    public ProductsdetailEntity save(@RequestBody ProductDetailDTO param){
        ProductsdetailEntity prddt = new ProductsdetailEntity();
        prddt.setId(param.getId());
        prddt.setIdMerchants(param.getId());
        prddt.setIdProduct(param.getId());
        prddt.setPrice1(new BigDecimal(param.getPrice1().intValue()));
        if(param.getStatus()==1){
            prddt.setPrice2(new BigDecimal(0));
        }else{
            prddt.setPrice2(new BigDecimal(param.getPrice2().intValue()));
        }
        return service.save(prddt);
    }

    @GetMapping("/find_id")
    public ProductDetailDTO getname(@RequestParam("id") Integer id){
        return service.findByID(id);
    }
}
