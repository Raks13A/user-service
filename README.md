**User Service – Spring Boot Microservice**
A production-ready **User Management Microservice** built using Spring Boot, featuring **JWT Authentication, Redis Caching, and Dockerized Deployment**.

This project demonstrates a real-world backend system with:
* Secure authentication using JWT
* High-performance data retrieval using Redis caching
* Clean layered architecture
* Containerized deployment using Docker

#Tech Stack

* Java 17
* Spring Boot
* Spring Security + JWT
* PostgreSQL
* Redis
* Maven
* Docker & Docker Compose

#Features

* JWT Authentication
* User CRUD APIs
* Redis Caching
* Global Exception Handling
* Dockerized Setup


#API Endpoints

*Authentication
POST /api/auth/login

*User APIs
GET /api/users/{id}
PUT /api/users/{id}
DELETE /api/users/{id}

---

#To Test

*Login
POST /api/auth/login

{
  "username": "admin",
  "password": "password"
}

*Use Token
Authorization: Bearer <TOKEN>

#Run with Docker
docker-compose up --build

#Key Learnings
* Implemented JWT authentication using Spring Security
* Designed caching layer using Redis
* Handled real-world debugging (403, JWT filter issues)
* Containerized application using Docker

Author
Rakshana A
Backend Developer (Java | Spring Boot)
