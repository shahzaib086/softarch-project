package com.example.processor.model.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;

    private String title ;

    private String category ;

    private Long price ;

    private String description ;
}
