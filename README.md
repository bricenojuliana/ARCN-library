# Library Management System

This project is a **Library Management System** developed using **Java** and **Spring Boot**, adhering to the principles of **Clean Architecture**. The system is designed to manage the operations of a library, including the registration of book copies and the handling of transactions such as book requests and returns. The architecture ensures a modular, maintainable, and testable codebase, making it easier to extend and adapt to future requirements. Additionally, the project incorporates a robust **CI/CD pipeline** using **GitHub Actions**, which automates the build and testing processes to maintain code quality and stability. The development process follows **Test-Driven Development (TDD)** for the core classes, ensuring that the business logic is reliable and thoroughly tested.

## Architecture

The project is structured following **Clean Architecture**, which emphasizes the separation of concerns by organizing the codebase into distinct layers. This approach ensures that the core business logic remains independent of external frameworks and technologies, making the system more maintainable and adaptable. The architecture is divided into the following layers:

1. **Domain Layer**: This is the core of the application, containing the business logic and domain models such as `Book`, `Copy`, and `Transaction`. It is completely independent of external libraries or frameworks, ensuring that the business rules are isolated and can evolve without being affected by changes in other layers.

2. **Application Layer**: This layer implements the use cases that define the application's behavior. For example, the `RegisterBookCopyUseCase` orchestrates the logic for registering a new book copy. The application layer acts as a bridge between the domain and infrastructure layers, ensuring that the business logic is executed correctly.

3. **Infrastructure Layer**: This layer handles external concerns such as database access, REST API controllers, and persistence. For instance, the `CopyController` in this layer exposes RESTful endpoints for managing book copies, while the `TransactionEntity` defines the database schema for transactions.

4. **Interface Layer**: This layer provides the entry points to the application, such as RESTful APIs, through which external systems or users can interact with the system.

By adhering to Clean Architecture, the project ensures that the core business logic is decoupled from external dependencies, making it easier to test, maintain, and extend.

## Features

The Library Management System offers the following key features:

- **Book Copy Management**: Allows the registration of new book copies using unique barcodes. This feature is implemented using a RESTful API that interacts with the `RegisterBookCopyUseCase` to save the book copy in the repository.
- **Transaction Management**: Facilitates the handling of book transactions, including requests, returns, and status tracking. This feature is still under development and will include endpoints for managing transactions.
- **Test-Driven Development (TDD)**: The core classes, such as use cases and domain models, are developed using TDD. This approach ensures that the business logic is thoroughly tested and reliable.
- **CI/CD Pipeline**: The project includes a GitHub Actions pipeline that automates the build and testing processes. This pipeline runs on every pull request and merge to the `main` branch, ensuring that the codebase remains stable and free of errors.

## CI/CD Pipeline

The CI/CD pipeline is an integral part of the project's **DevOps** process. It is implemented using **GitHub Actions** and automates the following tasks:

1. **Pull Request Validation**: Whenever a pull request is created, the pipeline automatically builds the project and runs all tests. This ensures that any new changes do not introduce errors or break existing functionality.

2. **Main Branch Validation**: On every merge to the `main` branch, the pipeline performs a full build and test cycle. This guarantees that the `main` branch remains stable and production-ready.

By automating these tasks, the pipeline reduces manual effort and ensures a consistent and reliable development process.

## Technologies Used

The project leverages the following technologies:

- **Java**: The primary programming language used for development.
- **Spring Boot**: A framework that simplifies the development of Java-based applications.
- **Maven**: A build tool and dependency management system.
- **JPA (Jakarta Persistence API)**: Used for database interaction and persistence.
- **Lombok**: A library that reduces boilerplate code by generating getters, setters, and constructors.
- **GitHub Actions**: A CI/CD tool used to automate the build and testing processes.

## Project Structure

The project is organized into the following directories:

- `src/main/java/eci/arcn/library/inventory`: Contains the logic for managing the library's inventory.
  - `infrastructure/api`: REST controllers for managing book copies, such as the `CopyController`.
  - `application/useCases`: Business logic for use cases, such as `RegisterBookCopyUseCase`.
  - `domain`: Domain models and repositories for books and copies.
- `src/main/java/eci/arcn/library/transaction`: Handles transaction-related logic.
  - `infrastructure`: Contains entity definitions for transactions, such as `TransactionEntity`.
- `src/test/java/eci/arcn/library`: Contains test cases for the application, including unit and integration tests.

## How to Run

To run the application, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/bricenojuliana/library-management-system.git
   cd library-management-system
   ```

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. Access the API at `http://localhost:8080`.

## API Endpoints

The project currently has **9 implemented endpoints**, including the **Book Management** endpoints:

### **Copy Management (`CopyController`)**
1. **POST** `/api/copies`: Registers a new book copy.

### **Transaction Management (`TransactionController`)**
2. **POST** `/api/transactions/request`: Requests a book.
3. **POST** `/api/transactions/return`: Returns a book.

### **User Management (`UserController`)**
4. **POST** `/api/users`: Registers a new user.
5. **DELETE** `/api/users/{id}`: Deletes a user.
6. **PUT** `/api/users/{id}`: Updates a user.

### **Book Management (`BookController`)**
7. **POST** `/api/books`: Registers a new book.
8. **GET** `/api/books/{id}`: Retrieves a book by its ID.
9. **DELETE** `/api/books/{id}`: Deletes a book by its ID.

## Testing

The project includes a comprehensive suite of tests to ensure code quality and reliability. To run the tests, use the following command:
```bash
mvn test
```

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
