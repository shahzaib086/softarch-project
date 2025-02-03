package com.example.publisher.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class RestHelper {

    public static Map<String, String> toJson(String key,String message) {
        Map<String, String> response = new HashMap<>();
        response.put(key, message);
        return response;
    }

}
