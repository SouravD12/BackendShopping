package com.myproject.backendshopping.commons;

import com.myproject.backendshopping.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Authenticationcommons {
    private RestTemplate restTemplate;
    public Authenticationcommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String token){
        ResponseEntity<UserDto>userDtoResponse = restTemplate.postForEntity("https://localhost:8181/users/validate/" +token,
                null,
                UserDto.class
                );
        if(userDtoResponse.getBody()==null) {
            return null;
        }
        return userDtoResponse.getBody();
    }
}
