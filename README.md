# Task Manager - Spring Boot

A RESTful Task Manager application built with Spring Boot, PostgreSQL, Spring Security, JWT, and Google OAuth 2.0/OIDC.

## Features

- User registration and login
- JWT-based authentication
- Google OAuth 2.0/OIDC login
- Automatic user creation for Google-authenticated users
- BCrypt password hashing
- Protected REST APIs
- Task CRUD operations
- Global exception handling
- Input validation
- PostgreSQL database integration
- ModelMapper for DTO mapping

## Authentication Flow

### Normal Login

```text
Email + Password
       ↓
AuthenticationManager
       ↓
UserDetailsService
       ↓
PostgreSQL
       ↓
JWT generated
       ↓
Bearer Token
       ↓
Protected APIs
```

### Google OAuth2 / OIDC Login

```text
Google Login
       ↓
Google OAuth 2.0 / OIDC
       ↓
OAuth2User
       ↓
Get email + name
       ↓
Find/Create User in PostgreSQL
       ↓
Generate Application JWT
       ↓
Bearer Token
       ↓
JWT Authentication Filter
       ↓
Protected REST APIs
```

## Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- OAuth 2.0 / OIDC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- ModelMapper
- Postman

## Security

- JWT-based authentication for REST APIs
- Google OAuth 2.0/OIDC authentication
- BCrypt password hashing
- Custom JWT authentication filter
- Custom UserDetailsService
- Protected REST endpoints

## API Authentication

Protected endpoints require a JWT Bearer token:

```text
Authorization: Bearer <JWT>
```

## Environment Variables

Sensitive configuration is provided through environment variables:

```text
DB_PASSWORD
JWT_SECRET
GOOGLE_CLIENT_ID
GOOGLE_CLIENT_SECRET
```