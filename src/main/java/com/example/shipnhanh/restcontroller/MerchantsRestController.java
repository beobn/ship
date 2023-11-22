package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.entity.MerchantsEntity;
import com.example.shipnhanh.service.impl.MerchantsImpl;
import jakarta.persistence.PersistenceException;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("admin/rest/merchant") @CrossOrigin("*")
public class MerchantsRestController {

    private final MerchantsImpl service;
    public MerchantsRestController(MerchantsImpl service) {
        this.service = service;
    }

    @GetMapping("/getall/{page}")
    public Page<MerchantsEntity> getALL(
            @PathVariable("page") Integer page,
            @RequestParam("seach") String seach
    ){
        if(seach.length()==0 || seach==null || seach.equals("undefined")){
            return service.findAll(page,5);
        }else{
            return service.findByName(page,5,seach);
        }
    }

    @PostMapping("/save")
    public MerchantsEntity save(@RequestBody MerchantsEntity param){
        return service.save(param);
    }

    @GetMapping("/find-id")
    public MerchantsEntity getname(@RequestParam("id") Long id){
        return service.findByID(id);
    }

}
