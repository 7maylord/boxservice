# BoxService
BoxService is a Spring Boot application that handles operations related to boxes and items. This service allows you to load items into boxes, perform CRUD operations, and manage relationships between boxes and items.

## Prerequisites
Before running the project, ensure you have the following installed on your machine:

- Java 17+ (or any version compatible with Spring Boot 3.x)
- Maven (for build and dependency management)
- PostgreSQL (or your preferred database, but PostgreSQL is recommended for this project)

## Getting Started
Follow these steps to set up, build, and run the boxservice project locally.

1. Clone the repository
    ```bash
    git clone https://github.com/7maylord/boxservice
    cd boxservice
    ```
2. Set up your database: This application uses H2 Database with database connection settings in the src/main/resources/application.  properties file:
    ```properties
    spring.datasource.url=jdbc:h2:mem:boxservice_db;DB_CLOSE_DELAY=-1
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=create-drop
    ````

    Alternatively, You can use  PostgreSql, Make sure you have a PostgreSQL database running. Create a database named boxservice_db (or any name you prefer), and update the database connection settings in the src/main/resources/application.properties file:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/boxservice_db
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.driver-class-name=org.postgresql.Driver
    ```

3. Build the project
    The project uses Maven as the build tool. To build the project, run the following command:

    ```bash
    ./mvnw clean install
    ```
    This will compile the source code, run any tests, and package the application into a JAR file.

4. Run the application
    Once the build is successful, you can run the application with:

    ```bash
    ./mvnw spring-boot:run
    ```
    The application will be accessible on http://localhost:8080.

5. Test the application
    The project uses JUnit 5 for unit and integration tests. To run the tests, use the following command:

    ```bash
    ./mvnw test
    ```
    This will execute all the test cases and display the results in the terminal.

6. Endpoints
    Once the application is running, you can use the following endpoints:

    - Create a box: POST /api/boxes
    - Load items into a box: PUT /api/boxes/{boxId}/load
    - Get box details: GET /api/boxes/available
    - Get a box's items: GET /api/boxes/{boxId}/items
    Refer to the  controller classes for more details.


## License
This project is licensed under the MIT License - see the LICENSE file for details.

