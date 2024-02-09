package com.myproject.backendshopping.repositories;

import com.myproject.backendshopping.models.Category;
import com.myproject.backendshopping.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category>findByName(String name);
//
    @Query("select c.name from Category c")
    List<String>getAllCategroies();

//    List<Category>findAllBy

}
