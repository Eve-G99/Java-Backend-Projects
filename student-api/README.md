# Student-API

This is a Spring Boot application that provides a CRUD API for managing students and teachers. The API allows you to create, retrieve, update, and delete students and teachers. Additionally, it supports querying students by title and teacher name.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- PostgreSQL database

### Installation

1. Clone the repository
2. Update the PostgreSQL configuration in `src/main/resources/application.properties`
3. Build the project:

    ```sh
    mvn clean install
    ```

4. Run the application:

    ```sh
    mvn spring-boot:run
    ```

## API Documentation

The API is documented using OpenAPI 3.0 and can be accessed via Swagger UI at `http://localhost:9000/swagger-ui.html`.

## Running the Tests

To run the tests, use the following command:

```sh
mvn test
