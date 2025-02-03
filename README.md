# Ecommerce Microservices Application

## Overview
This project is a Java-based microservices application composed of four distinct services:

1. API Service:
Purpose: Acts as the gateway for client interactions, exposing RESTful endpoints for external communication.
Role: Validates incoming requests, forwards them to the appropriate internal services, and aggregates responses when necessary.

2. Publisher Service:
Purpose: Facilitates asynchronous communication between microservices by publishing messages to a message broker.
Role: Receives events or data changes and publishes them to topics in the message broker, ensuring decoupled and scalable communication.

3. Processor Service:
Purpose: Handles the core business logic and processes data received from other services or directly from the message broker.
Role: Performs computations, data transformations, and interacts with the database to store or retrieve information.

4. Notification Service:
Purpose: Manages user notifications, ensuring timely alerts and messages are delivered through preferred channels.
Role: Subscribes to relevant topics in the message broker, processes notification events, and sends messages via email, SMS, or push notifications.

## Architecture
The application follows a microservices architecture, where each service operates independently and communicates with others through well-defined APIs or messaging queues. This design promotes scalability, maintainability, and flexibility.

**Key Decisions and Rationale**
- Database Choice:
  In our microservices architecture, each service utilizes its own H2 database instance for data storage and retrieval. H2 is a lightweight, open-source, in- 
  memory relational database management system written in Java. It's particularly well-suited for development, testing, and proof-of-concept scenarios due to its 
  speed and ease of integration.
  ### Features:
  > Browser-Based Console

  > Embedded Mode
  
- Communication Mechanisms
  RESTful APIs: Synchronous communication is achieved through RESTful APIs, allowing real-time request-response interactions between services when immediate 
                feedback is required.
  Apache Kafka: For asynchronous communication, Apache Kafka is utilized as the message broker. This choice enables decoupled, scalable, and fault-tolerant 
                message exchanges between services, enhancing system resilience.

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

## Setup and Running Instructions
Follow these steps to set up and run the application in IntelliJ IDEA:

1. Clone the Repository:
```
git clone https://github.com/shahzaib086/softarch-project/tree/main
```
2. Open the project in IntelliJ

3. Start Docker network for Kafka
   - Goto docker folder and run this command:
   ```
   docker compose up -d
   ```
   
5. Now Start All Services in IntelliJ

6. All APIs will be served at url:
   ```
   http://localhost:8080/api
   ```

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
