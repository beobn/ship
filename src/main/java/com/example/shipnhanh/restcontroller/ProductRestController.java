package com.example.shipnhanh.restcontroller;


import com.example.shipnhanh.entity.ProductsEntity;
import com.example.shipnhanh.repository.ProductRepository;
import com.example.shipnhanh.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("admin/rest/product")
@CrossOrigin("*")
public class ProductRestController {


    private final ProductRepository productRepository;
    private final ProductService productService;

    private List<ProductsEntity> productsEntities = null;

    public ProductRestController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/getall/{page}")
    public ResponseEntity<Page<ProductsEntity>> getALL(
            @PathVariable("page") Integer page,
            @RequestParam("nameProduct") String nameProduct){
        if(nameProduct.length()==0 || nameProduct==null){
            return ResponseEntity.ok ().body (productService.findAll(page,20));
        }else{
            return ResponseEntity.ok ().body (productService.findByName(page,20,nameProduct));
        }
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
    public ResponseEntity<List<String>> getTopProductRecently() throws IOException {
        List<String> nameListStr = productRepository.findProductRecently();
        if (nameListStr.isEmpty () || nameListStr.size () == 0){
            return  ResponseEntity.status (HttpStatus.NO_CONTENT).body (nameListStr);
        }
        return  ResponseEntity.ok ().body (nameListStr);
    }

    @GetMapping("/find-name-product")
    public ResponseEntity<ProductsEntity>  findByNameLike(@RequestParam("nameProduct")String nameProduct){
        if ( nameProduct.equals ("")){
            System.out.println ("null name product");
            return  ResponseEntity.badRequest ().build ();
        }
        return ResponseEntity.ok ().body (productRepository.findByNameLike (nameProduct).get ());
    }
}
