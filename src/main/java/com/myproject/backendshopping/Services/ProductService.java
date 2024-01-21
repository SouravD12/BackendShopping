package com.myproject.backendshopping.Services;

import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.dtos.FakeStoreProductDto;
import com.myproject.backendshopping.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id)throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product addNewProduct(FakeStoreProductDto fakeStoreProductDto);
    Product replaceProduct(Long id,FakeStoreProductDto fakeStoreProductDto);
//    Product partialUpdateProduct(Long id , FakeStoreProductDto fakeStoreProductDto);
}
