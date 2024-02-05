package com.myproject.backendshopping.Services;

import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.dtos.FakeStoreProductDto;
import com.myproject.backendshopping.models.Product;
import com.myproject.backendshopping.repositories.CategoryRepository;
import com.myproject.backendshopping.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addNewProduct(FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }
}
