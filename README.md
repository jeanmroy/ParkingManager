# Parking Manager API

This is a backend-only Spring Boot project designed to manage paid parking spaces in my personal yard. It provides a simple CRUD (Create, Read, Update, Delete) API for managing parking locations, owners, and vehicles.

## Features

- **CRUD Operations:** Manage parking locations, vehicle owners and vehicles through a RESTful API.
- **Local Profile:** The application is configured to run with a local profile, utilizing an in-memory H2 database for development purposes.
- **Actuator Health Endpoint:** Monitor the application's health status.
- **Backend Only:** This project is a backend API and does not include a frontend interface (yet).

## TO-DO

- **Prerequisites:** Test if the application is well running in JDK17. It's built under JDK21 and it would be great to have the minimal version required in the doc.
- **Property:** Add the `cost` property. As for now, there is nothing related to money in the application.
- **Testing:** Write/Complete unit and integration tests to validate validations and controllers.
- **Spring Security:** Enable Spring Security with username/password authentication.
- **Security Configuration:** Set up a security filter chain to protect API endpoints.
- **Secret Management:** Integrate a secret manager for sensitive data. (Vault, Github secrets?).
- **Docker:** Ensure that the docker file is still working and create a docker composite config file.
  
## Prerequisites

Before you can build and run this application, you'll need the following software installed on your system:

- **Java Development Kit (JDK) 21:**
  - This project is built using Eclipse Temurin Java 21. Ensure you have JDK 21 or a compatible version installed. You can download it from [Adoptium's website](https://adoptium.net/en-GB/temurin/releases/).
  - Verify your installation by running `java -version` in your terminal.
- **Apache Maven:**
  - Maven is used for building and managing the project's dependencies. It has be developed with Maven 3.9.9. While older versions may work, using a recent version is advised for optimal compatibility.
  - You can download Maven from the [Apache Maven website](https://maven.apache.org/download.cgi).
  - Verify your installation by running `mvn -version` in your terminal.
- **Docker (Optional):**
  - If you intend to run the application using Docker, you'll need Docker installed and running on your system. You can download Docker from [Docker's website](https://www.docker.com/products/docker-desktop/).

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

3.  **Build and Run with Docker Image (alternative):**

    - Package the application with the following command :

      ```bash
      mvn clean package
      ```

    - Ensure Docker is running.
    - From the project root directory, build the Docker image using docker command:

      ```powershell
      docker build -t parkingmanager-api .
      ```

    - This command will build the Docker image and tag it as parkingmanager-api.

    - Run the Docker container using the following command:

      ```powershell
      docker run -p 8080:8080 parkingmanager-api
      ```

    - This will start the application in a Docker container, mapping port 8080 of the container to port 8080 on your host machine. `http://localhost:8080`

## Actuator Health Endpoint

- **Browser:** Open your web browser and navigate to `http://localhost:8080/actuator/health`.
- **PowerShell:** Use the following command:

  ```powershell
  curl http://localhost:8080/actuator/health
  ```
