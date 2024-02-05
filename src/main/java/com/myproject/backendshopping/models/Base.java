package com.myproject.backendshopping.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
abstract class Base {
//    private Long id;
    @Id
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private boolean isDeleted;
}
