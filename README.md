# Data Table API
A minimal REST API for a dynamic data table system (like a tiny Airtable) built with *Java Spring Boot* and *SQLite*. Users can create tables with custom schemas and insert/retrieve rows dynamically.

## Features

- ✅ Create tables with custom column definitions (string, number, boolean)
- ✅ Insert rows with automatic type validation
- ✅ Retrieve all rows from a table
- ✅ SQLite persistence (no in-memory storage)
- ✅ Automatic table and column management
- ✅ Comprehensive error handling
- ✅ Type validation for all operations

## Technology Stack

- *Java 17* (or 11 with Spring Boot 2.x)
- *Spring Boot 3.1.5* (or 2.7.x)
- *Spring Data JPA* for database operations
- *SQLite* for lightweight persistence
- *Jackson* for JSON processing
- *Maven* for dependency management

## Prerequisites

- Java 17 or higher (Java 11 for Spring Boot 2.x)
- Maven 3.6+
- Git (optional)

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd data-table-api
### 2. Build and Run the Application

```bash'
mvn clean install
mvn spring-boot:run

### 3. Verify the API
verify


