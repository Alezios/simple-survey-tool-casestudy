# Simple Survey Tool

This project was developed as a case study and training exercise for **Spring Boot** and **Vue3**.

## Copy of the Subject: Development of a Simple Survey Tool

### Use Case

As a **Team Member**, I would like to rate questions of an anonymous survey to give my opinion about teamwork.

### Acceptance Criteria

- **Survey is displayed** at the URL `/surveys/[id]`.
- The user can **rate 5 questions**.
- **Survey results are stored** in a database.
- The survey results are accessible at the URL `/api/surveys/[id]/results` in **JSON format**.

---

## Architecture

- **Backend**: Spring Boot
- **API**: REST API
- **Frontend**: Vue3
- **Database**: Customizable (e.g., MariaDB)

---

## How to Start

1. Make sure you have **Docker** installed on your machine.
2. Navigate to the root of the project.
3. Run the following command in your terminal:

   ```bash
   docker-compose up -d --build
   ```

4. Access the Vue frontend in your web browser at:  
   [http://localhost:80/](http://localhost:80/)

---

## Design

### Backend

The **Spring Boot application** was built with **Hexagonal architecture** in mind.\
\
The `core` package represents the domain and business rules. It is designed to be as **clean code** as possible(SOLID principles, design patterns) and for **future-proofing**.\
\
The `core` package is **dependency-agnostic**:
- **Driving side**: Any type of controller can use the exposed business services.
- **Driven side**: Any repository can implement the persistence interface and be injected.\

The implemented controller is a REST API controller.\
The implemented repository uses Spring Boot JPA to manage entities and Flyway-core to manage database migrations

- **Implemented Components**:
  - **Controller**: The implemented controller is a **REST API controller**.
  - **Repository**: The implemented repository uses **Spring Boot JPA** to manage entities and **Flyway-core** to manage database migrations.

### Database

The database is a **MariaDB instance** running in its own Docker container.\
\
The backend connects to the database via the Docker network established during `docker-compose`.

### Frontend

The **Vue3 frontend** calls the backend REST API by using **Nginx reverse proxy** to redirect the api HTTP requests to the backend accessible through the docker network and avoid CORS problems.

Key Features:
- **TailwindCSS**: For beautiful and quick design.
- **Axios**: For promise-based HTTP requests.
- **SurveyJS Form Library**: To help display surveys.

---

## Still room for improvement

- **User Management**:
  - Add users, authentication, and privilege management.
  - Prevent users from answering a survey multiple times while keeping answers anonymous.
  - Regulate access to survey results.

- **Survey Creation**:
  - Add functionality to build new surveys.
  - Backend would be almost ready for this, minus some controller and repository update, the business logic for validation and question validity being already established.

- **Additional Enhancements**

---
