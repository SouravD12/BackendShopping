package com.myproject.backendshopping.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
abstract class Base {
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private boolean isDeleted;
}
