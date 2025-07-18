# User-Managment
# 🧑‍💻 Spring Boot User Management Microservice

This is a User Management RESTful API built using **Spring Boot** that supports user **registration**, **login**, **JWT-based authentication**, **role-based access control**, and **global exception handling**.

---

## 🔍 Features

- ✅ User registration and login with email/password
- 🔐 JWT authentication for securing APIs
- 👮 Role-based authorization (`ROLE_USER`, `ROLE_ADMIN`)
- 📦 Secured REST endpoints
- ⚠️ Global exception handling using `@ControllerAdvice`

---

## 🛠️ Tech Stack

- Java 8+
- Spring Boot
- Spring Security
- JWT (`jjwt` library)
- Maven
- MySQL (for database)

---

## 📌 API Endpoints

### 🔓 Public APIs

| Method | Endpoint     | Description         |
|--------|--------------|---------------------|
| POST   | `/register`  | Register new user   |
| POST   | `/login`     | Login and get JWT   |

> 🔹 Default role on registration: `ROLE_USER`

---

### 🔐 Secured APIs (JWT Required)

| Method | Endpoint         | Access Role(s)     |
|--------|------------------|--------------------|
| GET    | `/users`         | ADMIN only         |
| GET    | `/users/{id}`    | USER or ADMIN      |
| DELETE | `/users/{id}`    | ADMIN only         |

> Use `Authorization: Bearer <JWT Token>` in the header to access these APIs.

---

## 🔑 Authentication Flow

1. **Register** a user using `/register`
2. **Login** with the same user using `/login`
3. Copy the received JWT token
4. Use it in `Authorization` header for secured APIs

---

## 🧪 Testing the Application

You can test the APIs using **Postman**:

1. Register user:
    ```json
    POST /register
    {
        "name": "Rohidas",
        "email": "rohidas@example.com",
        "password": "rohi@123",
        "role": "ROLE_ADMIN"
    }
    ```

2. Login:
    ```json
    POST /login
    {
        "email": "rohidas@example.com",
        "password": "rohi@123"
    }
    ```

3. Use returned JWT token in the header:
    
    Authorization: Bearer <token>
    

---

## 📁 Project Structure

```java
com.example.demo
│
├── controller         --> REST controllers (AuthController, UserController)
├── entity             --> JPA entities (User, Role)
├── dto                --> DTO classes (AuthRequest, AuthResponse)
├── repository         --> Spring Data JPA repositories (UserRepository)
├── service
│   ├── JwtFilter      --> JWT validation filter
│   └── JwtUtil        --> Token generation/validation
├── securityConfig     --> Spring Security configuration
└── exception          --> Global exception handler


---

## ⚙️ Configuration

Update your `application.properties` file with the following:

``` application.properties
# Application Name
spring.application.name=User-Management-Microservice

# Server Port
server.port=8081

# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/user
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA & Hibernate
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true



---

## ⚙️ How to Run

### Using Maven
```bash
mvn spring-boot:run

``` in your IDE:

Right-click on the main class UserManagementMicroserviceApplication.java
Click Run As
        -> Spring boot app

