package com.myproject.backendshopping.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.backendshopping.Exceptions.CategoryNotFoundException;
import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.dtos.DeletedProductDto;
import com.myproject.backendshopping.dtos.FakeStoreProductDto;
import com.myproject.backendshopping.models.Category;
import com.myproject.backendshopping.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;


@Service("FakeStoreProductService")
@Getter
@Setter
public class FakeStoreProductService implements ProductService,CategoryService{

    private RestTemplate restTemplate;
    private WebClient.Builder webClientBuilder;
    private ObjectMapper objectMapper;


    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate,WebClient.Builder webClientBuilder,ObjectMapper objectMapper){
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
        this.objectMapper = objectMapper;
    }

    public Product convert(FakeStoreProductDto fakeStoreProductDto){
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
    public Product addNewProduct(Product product) {
//        Product p1 = convert(fakeStoreProductDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);

        ResponseEntity<Product> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.POST,
                requestEntity,
                Product.class
        );
        return responseEntity.getBody();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
//        Product p1 = convert(fakeStoreProductDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> requestEntity = new HttpEntity<>(product,httpHeaders);
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
        return webClientBuilder.clone()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs().jackson2JsonEncoder(
                                new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON)))
                        .build())
                .build()
                .patch()
                .uri("https://fakestoreapi.com/products/" + id)
                .body(BodyInserters.fromValue(product))
                .retrieve()
                .bodyToMono(FakeStoreProductDto.class)
                .map(this::convert)
                .block();
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
