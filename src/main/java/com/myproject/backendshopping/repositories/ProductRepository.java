package com.myproject.backendshopping.repositories;

import com.myproject.backendshopping.Services.ProductService;
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
    @Query(value = "select * from product p where id=:id",nativeQuery = true)
    Optional<Product> getProductWithId(Long id);

//    Product findById(Long id);


    Product save (Product product);

    @Query(value = "select * FROM product p",nativeQuery = true)
    List<Product>getAllProducts();

//    Hql Query
//    @Query("select p.description ,p.price  from Product p where p.price >=100000 and p.description like '%Latest%'")
//    List<ProductWithDescriptionAndPrice> productWithPriceAndDescription();
//
//    @Query(value = "select p.id as id ,p.title as title , p.price as price from Product p where id=102",nativeQuery = true)
//    List<ProductWithIdTitleAndPrice> productWithIdTitleAndPrice();

    List<Product> findAllByCategoryName(String name);
    void deleteById(Long id);

//    Native Query

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