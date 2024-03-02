package com.myproject.backendshopping;

import com.myproject.backendshopping.models.Product;
import com.myproject.backendshopping.repositories.ProductRepository;
import com.myproject.backendshopping.repositories.projections.ProductWithDescriptionAndPrice;
import com.myproject.backendshopping.repositories.projections.ProductWithIdTitleAndPrice;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test; //Using Junit framework of testing
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
class BackendShoppingApplicationTests {

    @Test
    void contextLoads() {
//        Spring Boot test , application context of springboot is running , or application is starting
    }

//    ERROR ;- no entity manager with transaction level found , added Transactional annotation
//    To commit the changes

}
