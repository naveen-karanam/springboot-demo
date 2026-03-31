# Data Table API

A minimal REST API for a dynamic data table system (like a tiny Airtable) built with Java Spring Boot and MySQL.

## Technology Stack
- Java 21
- Spring Boot 4.0.5
- Spring Data JPA
- MySQL
- Maven

## Prerequisites
- Java 21 or higher
- Maven 3.6+
- MySQL running locally

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/naveen-karanam/springboot-demo.git
cd springboot-demo
```

### 2. Create the MySQL Database
```sql
CREATE DATABASE tables;
```

### 3. Update application.properties
Edit `src/main/resources/application.properties` with your MySQL credentials:
```
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

The app will start on http://localhost:8080

## API Endpoints
See `api-tests-and-notes.txt` for full curl examples and design notes.


