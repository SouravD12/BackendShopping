package com.myproject.backendshopping.ControllerAdvices;

import com.myproject.backendshopping.Exceptions.CategoryNotFoundException;
import com.myproject.backendshopping.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleCategoryNotFound {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionDto>categoryNotFound(CategoryNotFoundException exception){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setDetails("Please check your category and retry");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
