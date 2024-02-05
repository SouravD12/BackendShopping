package com.myproject.backendshopping.repositories;

import com.myproject.backendshopping.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


}
