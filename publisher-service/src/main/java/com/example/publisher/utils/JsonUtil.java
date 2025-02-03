package com.example.publisher.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> fromJsonToList(String json, TypeReference<List<T>> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing JSON to list of objects with TypeReference", e);
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            // Remove curly braces and trim the string
            String cleanedJson = jsonString.replaceAll("[{}\"]", "").trim();

            // Split the key-value pairs
            String[] keyValuePairs = cleanedJson.split(",");

            // Populate a map with key-value pairs
            Map<String, String> jsonMap = new HashMap<>();
            for (String pair : keyValuePairs) {
                String[] entry = pair.split(":");
                if (entry.length == 2) {
                    jsonMap.put(entry[0].trim(), entry[1].trim());
                }
            }

            // Create an instance of the target class
            T instance = clazz.getDeclaredConstructor().newInstance();

            // Map the values to the class fields
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String value = jsonMap.get(field.getName());
                if (value != null) {
                    // Handle basic types
                    if (field.getType() == int.class || field.getType() == Integer.class) {
                        field.set(instance, Integer.parseInt(value));
                    } else if (field.getType() == long.class || field.getType() == Long.class) {
                        field.set(instance, Long.parseLong(value));
                    } else if (field.getType() == double.class || field.getType() == Double.class) {
                        field.set(instance, Double.parseDouble(value));
                    } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                        field.set(instance, Boolean.parseBoolean(value));
                    } else if (field.getType() == String.class) {
                        field.set(instance, value);
                    }
                }
            }
            return instance;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error converting JSON to object");
        }
    }
}
