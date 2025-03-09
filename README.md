# Parking Manager API

This is a backend-only Spring Boot project designed to manage paid parking spaces in my personal yard. It provides a simple CRUD (Create, Read, Update, Delete) API for managing parking locations, owners, and vehicles.

## Features

- **CRUD Operations:** Manage parking locations, vehicle owners and vehicles through a RESTful API.
- **Local Profile:** The application is configured to run with a local profile, utilizing an in-memory H2 database for development purposes.
- **Actuator Health Endpoint:** Monitor the application's health status.
- **Backend Only:** This project is a backend API and does not include a frontend interface (yet).

## TO-DO

- **Property:** Add the `cost` property.
- **Input Validation:** Implement robust validation in entities and services.
- **Testing:** Write unit and integration tests to validate validations and controllers.
- **Spring Security:** Enable Spring Security with username/password authentication.
- **Security Configuration:** Set up a security filter chain to protect API endpoints.
- **Secret Management:** Integrate a secret manager for sensitive data.

## How to Run

1.  **Clone the Repository:**

    ```bash
    git clone https://github.com/jeanmroy/ParkingManager.git
    cd parkingmanager
    ```

2.  **Build and Run with Maven:**

    - To run the application with the local profile and H2 database, use the following Maven command:

      ```bash
      mvn spring-boot:run
      ```

    - There is also a debug spring profile:

      ```bash
      mvn clean spring-boot:run -P debug
      ```

    - This will start the Spring Boot application on `http://localhost:8080`.

## Actuator Health Endpoint

- **Browser:** Open your web browser and navigate to `http://localhost:8080/actuator/health`.
- **PowerShell:** Use the following command:

  ```powershell
  curl http://localhost:8080/actuator/health
  ```
