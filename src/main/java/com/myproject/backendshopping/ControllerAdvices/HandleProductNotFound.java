package com.myproject.backendshopping.ControllerAdvices;

import com.myproject.backendshopping.Exceptions.ProductNotFoundException;
import com.myproject.backendshopping.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleProductNotFound {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto>productNotFound(ProductNotFoundException exception){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setDetails("Please check your id and retry");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
