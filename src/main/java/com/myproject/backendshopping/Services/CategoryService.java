package com.myproject.backendshopping.Services;

import com.myproject.backendshopping.Exceptions.CategoryNotFoundException;
import com.myproject.backendshopping.models.Category;
import com.myproject.backendshopping.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    public List<Product> getAllProductsInCategory(String name)throws CategoryNotFoundException;
    public List<String> getAllCategories();

}
