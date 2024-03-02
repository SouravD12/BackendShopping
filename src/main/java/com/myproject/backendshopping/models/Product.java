package com.myproject.backendshopping.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

@Getter
@Setter
@Entity
public class Product extends Base {
    private String title;
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER) //doesn't support Mapped By.
    private Category category;
    private String description;
    private String imageUrl;
    private boolean isDeleted;
}
