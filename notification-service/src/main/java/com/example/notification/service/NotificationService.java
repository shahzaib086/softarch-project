package com.example.notification.service;

public interface NotificationService {
    Boolean notify(Long receiverId, String title, String message) throws Exception;
}
