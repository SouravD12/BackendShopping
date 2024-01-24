package com.myproject.backendshopping.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletedProductDto {
    private Long id = 0l;
    private String title = " ";
    private double price = 0.0;
    private String category = " ";
    private String description = " ";
    private String image = " ";

}
