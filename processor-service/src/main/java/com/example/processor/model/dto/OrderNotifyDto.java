package com.example.processor.model.dto;

import lombok.Data;

@Data
public class OrderNotifyDto {

    private Long receiverId;

    private String title;

    private String message;


}
