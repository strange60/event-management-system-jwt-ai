# Event Management System

## Overview

A Spring Boot REST API for managing events with secure JWT-based authentication. The application supports event CRUD operations, search, pagination, sorting, validation, exception handling, PostgreSQL integration, Swagger documentation, and Docker deployment.

## Features

- User Registration & Login
- JWT Authentication
- Event CRUD Operations
- Event Search by Title
- Pagination & Sorting
- Input Validation
- Global Exception Handling
- DTO Pattern Implementation
- PostgreSQL Integration
- Spring Security
- REST APIs
- Swagger/OpenAPI Documentation
- Docker Containerization

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- PostgreSQL
- Spring Data JPA
- Maven
- Swagger / OpenAPI
- Docker
- Git & GitHub

## API Endpoints

### Authentication APIs

- POST `/auth/register`
- POST `/auth/login`

### Event APIs

- GET `/events`
- GET `/events/{id}`
- POST `/events`
- PUT `/events/{id}`
- DELETE `/events/{id}`
- GET `/events/sorted`

## Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── exception
└── resources
