package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.entity.AddressEntity;
import com.example.shipnhanh.repository.AddressrRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/address")
@CrossOrigin("*")
public class AddressRestController {
    private final AddressrRepository addressrRepository;

    public AddressRestController(AddressrRepository addressrRepository) {
        this.addressrRepository = addressrRepository;
    }

    @GetMapping("/get-all-address")
    public ResponseEntity<List<AddressEntity>> getAll(){
        return  ResponseEntity.ok ().body (addressrRepository.findAll ());
    }
}
