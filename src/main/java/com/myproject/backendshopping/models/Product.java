package com.myproject.backendshopping.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends Base {
    private String title;
    private double price;
    @ManyToOne
    private Category category;
    private String description;
    private String imageUrl;


}
