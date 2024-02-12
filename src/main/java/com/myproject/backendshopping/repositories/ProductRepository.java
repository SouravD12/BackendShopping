package com.myproject.backendshopping.repositories;

import com.myproject.backendshopping.models.Product;
import com.myproject.backendshopping.repositories.projections.ProductWithDescriptionAndPrice;
import com.myproject.backendshopping.repositories.projections.ProductWithIdTitleAndPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findById(Long id);

//    Product findById(Long id);


    Product save (Product product);

    @Query("select p FROM Product p")
    List<Product>getAllProducts();

//    Hql Query
    @Query("select p.description ,p.price  from Product p where p.price >=100000 and p.description like '%Latest%'")
    List<ProductWithDescriptionAndPrice> productWithPriceAndDescription();

    @Query("select p.id as id ,p.title as title , p.price as price from Product p where id=102")
    List<ProductWithIdTitleAndPrice> productWithIdTitleAndPrice();

    List<Product> findAllByCategoryName(String name);
    void deleteById(Long id);

//    Native Query
    @Query(value = "select p.title as title , from product p where p.id=202",nativeQuery = true)
    List<ProductWithIdTitleAndPrice>getProductTitle();

//    Product deleteAllById(Long id);

}



//In DB the ID is set via Database sequence internally , even if the rows gets deleted , the
//sequence isn't


//    Rather than using 2 different queries , used only one query to get the Product.

//    A database allows CRUD operations , for Read and Delete there are different ways
//    When it comes to update and create there is only 1 option "SAVE".

//    When calling the save method without an id , it is a direct creation of a product then
//    When calling the save method with an id , if the product exists then it is a save otherwise it is an update
//    EX --> Product save(Product p)