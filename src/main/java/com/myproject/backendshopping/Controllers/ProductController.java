package com.myproject.backendshopping.Controllers;


import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.Services.FakeStoreProductService;
import com.myproject.backendshopping.Services.ProductService;
import com.myproject.backendshopping.commons.Authenticationcommons;
import com.myproject.backendshopping.dtos.FakeStoreProductDto;
import com.myproject.backendshopping.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
//    private RestTemplate restTemplate;
    private Authenticationcommons authenticationcommons;


    @Autowired
//    To resolve the problem of two beans who are implementing the same interface
//    Either use @Qualifier or use @Primary.
//    For @Qualifier we have to mention the name in the constructor
//    But for @Primary we just to need to annotate at the top
    public ProductController(ProductService productService,Authenticationcommons authenticationcommons){
        this.productService = productService;
        this.authenticationcommons = authenticationcommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product>getSingleProduct(@PathVariable("id")Long id) throws ProductNotFoundException{
        return new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts(); // o p q

        List<Product> finalProducts = new ArrayList<>();

        for (Product p: products) { // o  p q
            p.setTitle(p.getTitle());
            finalProducts.add(p);
        }

        ResponseEntity<List<Product>> response = new ResponseEntity<>(
                finalProducts, HttpStatus.FORBIDDEN
        );
        return response;

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
