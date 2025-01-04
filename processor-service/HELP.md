# Processor Service

## Overview
Processor Service is a Spring Boot application that consumes messages from Kafka, processes them, and stores the results in an H2 database. Swagger-UI is available for API documentation.

---

## How to Run

1. **Start Kafka**:
   Use the  `docker-compose.yml` to start Kafka and Zookeeper:
---

## Access Points

- **Swagger-UI**: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- **H2 Console**: [http://localhost:8081/h2-console](http://localhost:8081/h2-console)

### H2 Database Credentials
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`