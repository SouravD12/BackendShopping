package com.myproject.backendshopping.Services;

import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.dtos.DeletedProductDto;
import com.myproject.backendshopping.dtos.FakeStoreProductDto;
import com.myproject.backendshopping.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product getSingleProduct(Long id)throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product addNewProduct(Product product);
    Product replaceProduct(Long id,Product product);
    void deleteProduct(Long id);
    Product updateProduct(Long id , Product product);
}
