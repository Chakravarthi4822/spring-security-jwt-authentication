# Spring Security JWT Authentication

A Spring Boot application demonstrating authentication, authorization, JWT-based security, and PostgreSQL integration.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Token)
- BCrypt Password Hashing
- Maven
- Postman

## Features

- User registration and login
- Secure password hashing using BCrypt
- PostgreSQL database integration
- JWT generation and validation
- Stateless authentication
- Role-based authorization
- Custom UserDetailsService
- Custom JWT authentication filter
- Protected endpoints for users and administrators

## How It Works

1. Users register with their credentials.
2. Passwords are hashed using BCrypt before being stored.
3. Users log in with their credentials.
4. The application generates a JWT after successful authentication.
5. Clients send the JWT in the Authorization header.
6. A custom security filter validates the token.
7. Spring Security checks the user's role before granting access.

## Configuration

Configure the following environment variables before running the application:

- `DB_PASSWORD` — PostgreSQL password
- `JWT_SECRET` — JWT signing secret

Update the PostgreSQL connection URL and username in `application.properties` if needed.

Never commit real passwords, signing secrets, or access tokens to GitHub.

## Run the Application

1. Install Java and PostgreSQL.
2. Create the PostgreSQL database configured in `application.properties`.
3. Set the required environment variables.
4. Open the project in Spring Tool Suite or another Java IDE.
5. Run the Spring Boot application.

## Testing

Use Postman to test registration, login, JWT authentication, and role-protected endpoints.

## Learning Outcomes

- Understanding Spring Security fundamentals
- Implementing database-backed authentication
- Using BCrypt for password hashing
- Implementing JWT-based authentication
- Configuring role-based authorization
- Integrating Spring Boot with PostgreSQL

## Author

Chakravarthi
