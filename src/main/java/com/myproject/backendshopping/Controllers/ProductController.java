package com.myproject.backendshopping.Controllers;


import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.Services.ProductService;
import com.myproject.backendshopping.dtos.FakeStoreProductDto;
import com.myproject.backendshopping.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Product addNewProduct(@RequestBody FakeStoreProductDto fakeStoreProductDto){
        return productService.addNewProduct(fakeStoreProductDto);
    }
}
