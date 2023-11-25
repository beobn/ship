package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.repository.ProductRepository;
import com.example.shipnhanh.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("admin/rest/product")
//@CrossOrigin("*")
public class ProductRestController {
    private final ProductRepository productRepository;
    private final ProductService productService;

//    private final List<ProductsEntity> productsEntities = null;


    public ProductRestController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }


    @PostMapping ("/save")
    public ProductsEntity save(@RequestBody ProductsEntity param){
        return productService.save(param);
    }

    @GetMapping("/find-product-by-id")
    public ResponseEntity<ProductsEntity> getProductDetail(@RequestParam(required = false) Long id){
        if(id==null){
            return  ResponseEntity.badRequest ().build ();
        }
        Optional<ProductsEntity> optionalProducts   = productService.findByID (id);
        if(optionalProducts.isPresent ()){
            return ResponseEntity.ok ().body (optionalProducts.get ());
        }
        return  ResponseEntity.badRequest ().build ();
    }


    @GetMapping("/find-top-product-recently")
    public ResponseEntity<List<String>> getTopProductRecently()  {
        List<String> nameListStr = productRepository.findProductRecently();
        if (nameListStr.isEmpty ()){
            return  ResponseEntity.status (HttpStatus.NO_CONTENT).body (nameListStr);
        }
        return  ResponseEntity.ok ().body (nameListStr);
    }

    @GetMapping("/find-name-product")
    public ResponseEntity<List<ProductsEntity>> findByNameLike(@RequestParam("nameProduct")String nameProduct){
        if ( nameProduct.isEmpty()){
            System.out.println ("null name product");
            return  ResponseEntity.badRequest ().build ();
        }
        List<ProductsEntity>  listEntitiesProduct  = productRepository.findByNameLike (nameProduct);
        for (ProductsEntity entityProductsEntity:  listEntitiesProduct ) {
            Optional<ProductsEntity> optionalProductsEntity = productRepository.findByName (nameProduct);
            optionalProductsEntity.orElseThrow ().setCountSeach (entityProductsEntity.getCountSeach ()+1);
            productRepository.save (optionalProductsEntity.get ());
        }
        return ResponseEntity.ok ().body (listEntitiesProduct);
    }

    @GetMapping("/getall-product-detail")  // dùng api này get data  page
    public ResponseEntity<List<ProductDetailDTO>> getALLProductAndMechances(
            @RequestParam(required = false)  Long longitude,
            @RequestParam(required = false) Long latitude){
//        if(longitude == null || latitude == null){
//            return ResponseEntity.badRequest ().build ();
//        }
        return ResponseEntity.ok ().body (productService.findAllProduct (longitude,latitude));
    }
}
