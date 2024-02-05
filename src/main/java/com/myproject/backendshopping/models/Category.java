package com.myproject.backendshopping.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends Base {

    private String name;
//
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}



