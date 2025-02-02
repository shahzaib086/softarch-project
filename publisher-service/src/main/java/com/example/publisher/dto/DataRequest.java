package com.example.publisher.dto;

import lombok.Data;

@Data
public class DataRequest {
    private String filter;
    private int page;

    // Getters and setters (or use Lombok annotations)
}