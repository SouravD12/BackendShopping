package com.myproject.backendshopping.Controllers;

import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.Services.ProductService;
import com.myproject.backendshopping.Services.SelfProductService;
import com.myproject.backendshopping.models.Product;
import com.myproject.backendshopping.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when; //Framework for Mocking in Java

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private SelfProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void testProductsSameAsService(){
        List<Product>products = new ArrayList<>();

////        Adding first product
//        Product p1 = new Product();
//        p1.setTitle("Iphone 14 Pro Max");
//        products.add(p1);
//
////        Adding second product
//        Product p2 = new Product();
//        p2.setTitle("Iphone 15 Pro Max");
//        products.add(p2);
//
////        Adding third product
//        Product p3 = new Product();
//        p3.setTitle("Playstation 5");
//        products.add(p3);


//        Arrange
        when(
                productService.getAllProducts()
        ).thenReturn(
                products
        );

//        Act
//       List<Product>response = productController.getAllProducts();

//       Assert
//        List<Product>productsInResponse = response;
//        assertEquals(products.size(),productsInResponse.size()); //Expected comes first and then actual


    }

//    @Test
//    void testNonExistingProduct(){
//        when(
//                productRepository.findById(10L)
//        ).thenReturn(
//                Optional.empty()
//        );
//        assertThrows(
//                ProductNotFoundException.class,
//                ()->productController.getSingleProduct(10L));
//    }
}