package com.myproject.backendshopping.Controllers;

import com.myproject.backendshopping.Exceptions.CategoryNotFoundException;
import com.myproject.backendshopping.Services.CategoryService;
import com.myproject.backendshopping.models.Category;
import com.myproject.backendshopping.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController( CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/category/{name}")
    public List<Product> getAllProductInCategory(@PathVariable("name") String name)throws CategoryNotFoundException {
        return categoryService.getAllProductsInCategory(name);
    }
    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
