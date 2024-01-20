package com.myproject.backendshopping.Exceptions;

import com.myproject.backendshopping.Controllers.CategoryController;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(String message){
        super(message);
    }
}
