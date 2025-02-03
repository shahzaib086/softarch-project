# Ecommerce Microservices Application

## Overview
This project is a Java-based microservices application composed of four distinct services:

- **API Service:** 
Serves as the main entry point for client requests, handling HTTP requests and routing them to the appropriate services.
- **Publisher Service:** 
Manages the publishing of messages to a message broker, facilitating communication between services.
- **Processor Service:** 
Processes incoming messages, performs business logic, and interacts with the database.
- **Notification Service:**
Sends notifications to users via various channels such as email, SMS, or push notifications.

## Architecture
The application follows a microservices architecture, where each service operates independently and communicates with others through well-defined APIs or messaging queues. This design promotes scalability, maintainability, and flexibility.

**Key Decisions and Rationale**
- Microservices Architecture: 
  Chosen to allow independent deployment and scaling of services, facilitating better management of complex applications.

- Message Broker:
  Implemented to decouple services and ensure reliable communication between them.

- Technology Stack: 
  Utilized Java with Spring Boot for rapid development and integration capabilities.

## Contributions
Shahzaib Ahmed SIDDIQUI
- Worked on Order management and Payment Management (Publisher and Processor services)
- Factory pattern implementation for different payment services (Master and Visa)
- Processor Service for Order and Payment

Syed Bilal RASHID
- Worked on Product Management and Listing (Publisher and Processor services)
- Processor service of Products
- Product Schema and Data Transfer Object Classes

Haleema USHAQ
- Worked on Notification Service and processor service
- NotificationService Interface and EmailNotification Service class implementation with singleton pattern.
- Worked on Order and OrderItem entity classes

Muhammad Nasir 
- Worked on Api service controller
- Worked on Repository pattern implementation for Database
- Json Parse utility class

# Architecture Diagram

![SA-Project drawio](https://github.com/user-attachments/assets/7f700b32-ef5f-4604-9ce5-8e879ec30269)

# Docker Configuration File
```
version: '3.8'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
  kafka:
    image: confluentinc/cp-kafka:latest
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```
