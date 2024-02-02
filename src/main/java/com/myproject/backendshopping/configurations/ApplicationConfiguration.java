package com.myproject.backendshopping.configurations;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplateBuilder().build();
    }
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
    private ObjectMapper objectMapper;
}
