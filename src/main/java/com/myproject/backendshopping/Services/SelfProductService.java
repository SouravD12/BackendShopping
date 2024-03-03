package com.myproject.backendshopping.Services;

import com.myproject.backendshopping.Exceptions.CategoryNotFoundException;
import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.dtos.FakeStoreProductDto;
import com.myproject.backendshopping.models.Category;
import com.myproject.backendshopping.models.Product;
import com.myproject.backendshopping.repositories.CategoryRepository;
import com.myproject.backendshopping.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Service("SelfProductService")
public class SelfProductService implements ProductService,CategoryService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.getProductWithId(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product id " + id + " doesn't exist");
        }
        Product product = productOptional.get();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.getAllProducts();
        return products;
    }

    @Override
    public Product addNewProduct(Product product) {
        Category category = product.getCategory();
        if(category.getId()==null) {
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory()
                .getName());
        if(categoryOptional.isEmpty()){
            product.setCategory(categoryRepository.save(product.getCategory()));
        }
        else {
            product.setCategory(categoryOptional.get());
        }
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product>productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new RuntimeException();
        }
        Product productInDB = productOptional.get();
        productInDB.setId(product.getId());
        productInDB.setTitle(product.getTitle());
        productInDB.setPrice(product.getPrice());
        productInDB.setDescription(product.getDescription());
        productInDB.setImageUrl(product.getImageUrl());
        return productRepository.save(productInDB);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.getProductWithId(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException();
        }
        Product savedProduct = productOptional.get();
        if (product.getDescription() != null) {
            savedProduct.setDescription(product.getDescription());
        }
        if (product.getTitle() != null) {
            savedProduct.setTitle(product.getTitle());
        }
        if (product.getPrice() != null) {
            savedProduct.setPrice(product.getPrice());
        }
        if (product.getImageUrl() != null) {
            savedProduct.setImageUrl(product.getImageUrl());
        }
        return productRepository.save(savedProduct);
    }

    @Override
    public List<Product> getAllProductsInCategory(String name) throws CategoryNotFoundException {
        List<Product> products = productRepository.findAllByCategoryName(name);
        if(products.size()==0){
            throw new CategoryNotFoundException(name +" category doesn't exist");
        }
        return products;
    }
    @Override
    public List<String> getAllCategories() {
        return categoryRepository.getAllCategroies();
    }

}




