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

    @GetMapping("/find-id")
    public ResponseEntity<ProductsEntity> getProductDetail(@RequestParam("id") Integer id){
        return ResponseEntity.ok ().body (productService.findByID(id));
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
    public ResponseEntity<ProductsEntity>  findByNameLike(@RequestParam("nameProduct")String nameProduct){
        if ( nameProduct.isEmpty()){
            System.out.println ("null name product");
            return  ResponseEntity.badRequest ().build ();
        }
        Optional<ProductsEntity>  optionalProductsEntity  = productRepository.findByName (nameProduct);
        return ResponseEntity.ok ().body (optionalProductsEntity.isPresent() ? optionalProductsEntity.get() : null);
    }

    @GetMapping("/getall-product-detail/{page}")  // dùng api này get data  page
    public ResponseEntity<Page<ProductDetailDTO>> getALLProductAndMechances(
            @PathVariable("page") Integer page,
            @RequestParam("nameProduct") String nameProduct,
            @RequestParam("longitude")  Long longitude,
            @RequestParam("latitude") Long latitude){
            return ResponseEntity.ok ().body (productService.findAllProduct (page,20,nameProduct,longitude,latitude));
    }
}
