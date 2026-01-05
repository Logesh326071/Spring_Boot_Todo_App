# Spring Boot ToDo Application (JWT Authentication)

A secure and scalable **ToDo application** built using **Spring Boot** with **JWT-based authentication**.
The application allows users to **register**, **login**, and **manage their personal ToDo tasks** after authentication.

---

## Features

- User Registration
- User Login with JWT Authentication
- Secure APIs using Spring Security
- Create, Read, Update, Delete (CRUD) ToDo items
- JWT Token validation for protected endpoints
- RESTful API design

---

##  Tech Stack

- **Backend**: Spring Boot
- **Security**: Spring Security, JWT
- **Database**: MySQL
- **ORM**: Spring Data JPA (Hibernate)
- **Build Tool**: Maven
- **Java Version**: Java 21
- **Frontend**: HTML, CSS, JavaScript

---

##  Project Structure

src/main/java/com/todo/ToDo_App
│
├── Controller
│   ├── AuthController.java
│   ├── TodoController.java
│
├── Service
│   ├── UserService.java
│   ├── TodoService.java
│
├── Repository
│   ├── UserRepository.java
│   ├── TodoRepository.java
│
├── Entity
│   ├── User.java
│   ├── Todo.java
│
├── Security
│   ├── JwtFilter.java
│   ├── JwtUtil.java
│   ├── SecurityConfig.java
│
└── ToDoAppApplication.java

---

##  Authentication Flow (JWT)

1. User registers using Register API
2. User logs in using Login API
3. Server generates a JWT token
4. Client stores the token
5. Token is sent in Authorization header:
   ```
   Authorization: Bearer <JWT_TOKEN>
   ```
6. JWT filter validates the token

---

##  API Endpoints

###  Authentication

| Method | Endpoint       | Description       |
|------  |---------       |------------       | 
| POST   | /auth/register | Register new user |
| POST   | /auth/login    | Login and get JWT |

###  ToDo Management (Protected)

| Method | Endpoint            | Description   |
|------  |---------            |------------   |
| GET    | /api/v1/todo        | Get all todos |
| POST   | /api/v1/todo/create | Create todo   |
| PUT    | /api/v1/todo        | Update todo   |
| DELETE | /api/v1/todo/{id}   | Delete todo   |

---

##  Request Examples

### Register
json
{
  "email": "logesh@example.com",
  "password": "password123"
}


### Login
json
{
  "email": "logesh@example.com",
  "password": "password123"
}

---

## Application Configuration

Update `application.properties`:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

---

## How to Run the Application

This project contains two directories:

backend – Spring Boot application

frontend – HTML, CSS, JavaScript
Both must be run separately.

## Run Backend (Spring Boot - IntelliJ IDEA)
## Run Frontend (VSCode - Live Server)

---

## Author

**Logesh Palanisamy**

---

