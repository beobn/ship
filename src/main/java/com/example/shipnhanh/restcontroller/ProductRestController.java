package com.example.shipnhanh.restcontroller;

import com.example.shipnhanh.DTO.ProductDetailDTO;
import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.repository.ProductRepository;
import com.example.shipnhanh.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin/rest/product")
@CrossOrigin("*")
public class ProductRestController {
    private final ProductRepository productRepository;
    private final ProductService productService;


    public ProductRestController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @PostMapping ("/save")
    public ProductsEntity save(@RequestBody ProductsEntity param){
        return productService.save(param);
    }

    @GetMapping("/find-product-by-id")
    public ResponseEntity<ProductDetailDTO> getProductDetail(@RequestParam(required = false) Long id){
        Optional<ProductDetailDTO> optionalProducts   = productService.findByID (id);
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
    public ResponseEntity<List<ProductDetailDTO>> findByNameLike(@RequestParam(required = false)String nameProduct){
        if ( nameProduct.isEmpty()){
            System.out.println ("nameProduct null");
            return  ResponseEntity.badRequest ().build ();
        }
        List<ProductDetailDTO>  listEntitiesProduct = productRepository.findByNameLike (nameProduct);
        for (ProductDetailDTO entityProductsEntity:  listEntitiesProduct ) {
            Optional<ProductsEntity> optionalProductsEntity = productRepository.findByName (nameProduct);
            if(optionalProductsEntity.isPresent ()){
                optionalProductsEntity.orElseThrow ().setCountSeach (entityProductsEntity.getCountSeach ()+1);
                productRepository.save (optionalProductsEntity.get ());
            }
        }
        return ResponseEntity.ok ().body (listEntitiesProduct);
    }

    @GetMapping("/getall-product-detail")  // dùng api này get data  page
    public ResponseEntity<List<ProductDetailDTO>> getALLProductAndMechances(
            @RequestParam(required = false)  String longitude,
            @RequestParam(required = false) String latitude){
        return ResponseEntity.ok ().body (productService.findAllProduct (Double.parseDouble (longitude),Double.parseDouble (latitude)));
    }

    @GetMapping("/getall-product-by-mechanse")
    public ResponseEntity<List<ProductDetailDTO>> getAllProductByMachanse(
            @RequestParam(required = false) Long idMerchants
    ){
        return ResponseEntity.ok ().body (productRepository.findByIdMatchanse (idMerchants));
    }
}
