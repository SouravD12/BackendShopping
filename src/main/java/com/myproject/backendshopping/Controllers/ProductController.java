package com.myproject.backendshopping.Controllers;


import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.Services.FakeStoreProductService;
import com.myproject.backendshopping.Services.ProductService;
import com.myproject.backendshopping.dtos.FakeStoreProductDto;
import com.myproject.backendshopping.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
//    To resolve the problem of two beans who are implementing the same interface
//    Either use @Qualifier or use @Primary.
//    For @Qualifier we have to mention the name in the constructor
//    But for @Primary we just to need to annotate at the top
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product>getSingleProduct(@PathVariable("id")Long id) throws ProductNotFoundException{
        return new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();

    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id")Long id, @RequestBody Product product){
        return productService.replaceProduct(id,product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id ,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id")Long id){
        productService.deleteProduct(id);
    };

}
