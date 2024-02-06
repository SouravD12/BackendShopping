package com.myproject.backendshopping;

import com.myproject.backendshopping.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class BackendShoppingApplicationTests {

    @Autowired
    private ProductRepository productRepository;
//    public BackendShoppingApplicationTests

    @Test
    void contextLoads() {
//        Spring Boot test , application context of springboot is running , or application is starting
    }
    @Test
    @Transactional
    @Commit
//    ERROR ;- no entity manager with transaction level found , added Transactional annotation
//    To commit the changes
    void testQueries(){
      productRepository.findByTitleContaining("Sourav");
      productRepository.deleteByTitle("Sourav");
    }



}
