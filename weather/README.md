# Weather Microservice Project

This project is a Spring Boot based microservice application. It includes multiple modules/services such as `common`, `library`, `university`, `discovery`, `gateway`, `search`, `config`, and `security`. Each module is a separate microservice that communicates with each other to perform specific tasks.

## Table of Contents

1. [Modules](#modules)
2. [Requirements](#requirements)
3. [Setup and Installation](#setup-and-installation)
4. [Running the Application](#running-the-application)
5. [Test](#Test)


## Modules

- **common**: Shared configurations and utilities.
- **library**: Handles book-related CRUD operations and uses PostgreSQL for persistence.
- **university**: Fetches university information using a third-party API.
- **discovery**: Eureka server for service discovery.
- **gateway**: API Gateway for routing requests to appropriate services.
- **search**: Aggregates data from `library` and `university` services.
- **config**: Centralized configuration service using Spring Cloud Config.
- **security**: Manages authentication and authorization using JWT.

## Requirements

- Java 8
- Maven
- PostgreSQL
- Spring Boot 2.1.7.RELEASE
- Spring Cloud Greenwich.SR2

## Setup and Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-repo/weather-microservice.git
    cd weather-microservice
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Set up PostgreSQL:
    - Create a database named `library_db`.
    - Update `application.properties` in the `library` module with your PostgreSQL credentials.

## Running the Application

Start the services in the following order:

1. **Config Service**:
    ```sh
    cd config
    mvn spring-boot:run
    ```

2. **Discovery Service**:
    ```sh
    cd discovery
    mvn spring-boot:run
    ```

3. **Other Services**:
    - **Library Service**:
        ```sh
        cd library
        mvn spring-boot:run
        ```
    - **University Service**:
        ```sh
        cd university
        mvn spring-boot:run
        ```
    - **Search Service**:
        ```sh
        cd search
        mvn spring-boot:run
        ```
    - **Gateway Service**:
        ```sh
        cd gateway
        mvn spring-boot:run
        ```
    - **Security Service**:
        ```sh
        cd security
        mvn spring-boot:run
        ```

## Test
**Security Service**:
    1. Test the public endpoint:GET http://localhost:8400/api/public
    2. Obtain a JWT token:POST http://localhost:8400/auth/login -H "Content-Type: application/json" -d '{"username":"user","password":"password"}'
    3. Use the user JWT token to access the user endpoint:GET http://localhost:8400/api/user -H "Authorization: Bearer <your_jwt_token>"
    4. Use the admin JWT token to access the admin endpoint:GET http://localhost:8400/api/admin -H "Authorization: Bearer <your_jwt_token>"
    5. Cannot get access to admin/user endpoint with user/admin jwt token

