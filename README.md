# Voting Application

## Description
A simple and clean voting management system built with **Spring Boot 4**.  
It allows creating elections, registering voters, and managing voting options, showcasing MVC architecture, 
service layer separation and global exception handling.

---

## Overview
The application demonstrates maintainable approach to building a backend system for elections and voting, 
including validation, entity mapping, and testing best practices.

---

## Features
- Create and manage elections
- Register voters
- Add and manage voting options
- Input validation with `javax.validation` annotations
- Centralized exception handling via `GlobalExceptionHandler`
- Unit and API endpoints tests for services and controllers

---

## Architecture
- **Controller Layer** – Handles HTTP requests
- **Service Layer** – Contains business logic
- **Repository Layer** – Interfaces with JPA/PostgreSQL database
- **Mapper Layer** – Converts between entities and DTOs
- **Entity Layer** – Defines JPA entities with UUID identifiers

---

## Development Approach
- **Spring Boot 4** with **Java 21**
- **Unit tests** using `JUnit 5` and `AssertJ`
- **Controller tests** using `MockMvc`
- **DTOs and mappers** for clean separation between API and entities

---

## API Overview
- `POST /v1.0/election/create` — Create a new election
- `PATCH /v1.0/election/vote` — Vote in election
- `POST /v1.0/voter/create` — Create a new voter
- `PATCH /v1.0/voter/update` — Update voter status

---

## Project Structure
```
src/main/java/com/voting/
├── controller       # REST controllers
├── exception        # Custom exceptions
├── mapper           # DTOs to entity mappers
├── dto              # Data Transfer Objects
├── entity           # JPA entities
├── type             # Application enums
├── repository       # JPA repositories
├── service          # Business logic
└── util             # Application constans
```

---