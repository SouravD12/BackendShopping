package com.myproject.backendshopping.Services;

import com.myproject.backendshopping.Exceptions.CategoryNotFoundException;
import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.dtos.DeletedProductDto;
import com.myproject.backendshopping.dtos.FakeStoreProductDto;
import com.myproject.backendshopping.models.Category;
import com.myproject.backendshopping.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import java.util.*;


@Service
public class FakeStoreProductService implements ProductService,CategoryService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convert(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        return product;
    }


    @Override
    public Product getSingleProduct(Long id)throws ProductNotFoundException{
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);

        if(fakeStoreProductDto==null){
            throw new ProductNotFoundException(
                    "Product with this " + id +" doesn't exist"
            );
            }
        return convert(fakeStoreProductDto);
        }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDto dto: response){
            answer.add(convert(dto));
        }
        return answer;
    }

    @Override
    public Product addNewProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product p1 = convert(fakeStoreProductDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Product> requestEntity = new HttpEntity<>(p1, headers);

        ResponseEntity<Product> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.POST,
                requestEntity,
                Product.class
        );
        return responseEntity.getBody();
    }

    @Override
    public Product replaceProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {
        Product p1 = convert(fakeStoreProductDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> requestEntity = new HttpEntity<>(p1,httpHeaders);
        ResponseEntity<Product>responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" +id,
                HttpMethod.PUT,
                requestEntity,
                Product.class
        );
        return responseEntity.getBody();
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        Category category = product.getCategory();
        if(category!=null){
            fakeStoreProductDto.setCategory(category.getName());
        }
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/"+id,
                HttpMethod.PATCH,
                requestCallback,
                responseExtractor
                );
        return convert(response);
    }


//    ---- Category Services ------

    @Override
    public List<Product> getAllProductsInCategory(String name)throws CategoryNotFoundException {
        FakeStoreProductDto[] response = restTemplate.getForObject("https://fakestoreapi.com/products/category/" + name,
                FakeStoreProductDto[].class);
        if(response.length==0){
            throw new CategoryNotFoundException(
                    "Category " +name+ " doesn't exist"
            );
        }
        List<Product>answer = new ArrayList<>();
        for(FakeStoreProductDto dto: response){
            answer.add(convert(dto));
        }
        return answer;
    }

    @Override
    public List<String> getAllCategories() {
        String[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                String[].class);
        return Arrays.asList(response);

    }

}