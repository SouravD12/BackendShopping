package com.myproject.backendshopping.repositories;

import com.myproject.backendshopping.dtos.FakeStoreProductDto;
import com.myproject.backendshopping.models.Category;
import com.myproject.backendshopping.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findById(Long id);

//    Declared query to find the title containing certain word
    List<Product> findByTitleContaining(String word);

    long deleteByTitle(String title);

    List<Product>findByTitleAndDescription(String title,
                                           String description);
    List<Product>findByPriceBetween(double startPrice, double endPrice);

//    Here ORM will do the join itself between 2 tables.
    List<Product>findByCategory(Category category);

    List<Product>findByCategory_Id(Long id);
//    Rather than using 2 different queries , used only one query to get the Product.

//    A database allows CRUD operations , for Read and Delete there are different ways
//    When it comes to update and create there is only 1 option "SAVE".

//    When calling the save method without an id , it is a direct creation of a product then
//    When calling the save method with an id , if the product exists then it is a save otherwise it is an update
//    EX --> Product save(Product p)

    Product save (Product product);

}



//In DB the ID is set via Database sequence internally , even if the rows gets deleted , the
//sequence isn't