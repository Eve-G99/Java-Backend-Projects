# University API Project

This is a Spring Boot application that interacts with the [Hipolabs University API](http://universities.hipolabs.com) to retrieve information about universities based on user-specified countries. The application provides two main API endpoints.

## Project Features

1. **Fetch Universities by Country**: This endpoint allows users to provide a country as input and get a list of universities from that country.
2. **Fetch Universities for Multiple Countries**: Users can provide multiple countries at once, and the application will make concurrent requests for each country and return aggregated results.

## Technologies Used

- **Java 17**
- **Spring Boot**
- **RestTemplate** for interacting with the third-party API
- **CompletableFuture** for handling asynchronous operations
- **Lombok** for reducing boilerplate code

## How to Run

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/university-api.git
    cd university-api
    ```

2. Build and run the application:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

3. Access the APIs:

   - **Get Universities by Country**: 
     - `GET http://localhost:8080/universities?country=United+Kingdom`
   - **Get Universities for Multiple Countries**: 
     - `POST http://localhost:8080/universities` with a JSON body:
     ```json
     ["United Kingdom", "Germany", "France"]
     ```

## API Documentation

### `GET /universities`

Retrieve a list of universities by specifying a country.

**Query Parameters**:

- `country` (required): The name of the country.
