package com.myproject.backendshopping.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category extends Base {
    private String name;
//
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}



