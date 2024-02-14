package com.myproject.backendshopping.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends Base {
    private String title;
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.PERSIST) //doesn't support Mapped By.
    private Category category;
    private String description;
    private String imageUrl;
}
