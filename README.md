# Student Management API

A Spring Boot REST API for managing student records with PostgreSQL and Spring Data JPA.

## How It Works

- **Architecture**: Controller (`StudentController`) &rarr; Service (`StudentService`) &rarr; Repository (`StudentRepository`) &rarr; PostgreSQL DB (`assessment`).
- **Port**: `8989` (Base URL: `http://localhost:8989/api/students`)
- **Data Validation**:
  - `name`: Not blank, max 255 chars
  - `email`: Not blank, valid email, unique
  - `age`: Required, minimum 18
  - `course`: Not blank, max 255 chars

---

## Getting Started

1. Ensure PostgreSQL is running and database `assessment` exists:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/assessment
   spring.datasource.username=postgres
   spring.datasource.password=root
   ```
2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## API Endpoints

### 1. Create Student
- **Method**: `POST`
- **URL**: `/api/students`
- **Request Body**:
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "age": 21,
    "course": "Computer Science"
  }
  ```
- **Response** (`201 Created`):
  ```json
  {
    "process": true,
    "message": "Student has been created!"
  }
  ```

---

### 2. Get All Students
- **Method**: `GET`
- **URL**: `/api/students`
- **Response** (`200 OK`):
  ```json
  [
    {
      "name": "John Doe",
      "email": "john.doe@example.com",
      "age": 21,
      "course": "Computer Science"
    }
  ]
  ```

---

### 3. Get Student by ID
- **Method**: `GET`
- **URL**: `/api/students/{id}`
- **Example**: `/api/students/1`
- **Response** (`200 OK`):
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "age": 21,
    "course": "Computer Science"
  }
  ```

---

### 4. Update Student by ID
- **Method**: `PUT`
- **URL**: `/api/students/{id}`
- **Example**: `/api/students/1`
- **Request Body**:
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "age": 22,
    "course": "Software Engineering"
  }
  ```
- **Response** (`202 Accepted`):
  ```json
  {
    "process": true,
    "message": "Student has been updated!"
  }
  ```

---

### 5. Delete Student by ID
- **Method**: `DELETE`
- **URL**: `/api/students/{id}`
- **Example**: `/api/students/1`
- **Response** (`202 Accepted`):
  ```json
  {
    "process": true,
    "message": "Student has been deleted!"
  }
  ```

---

### 6. Get Students by Course
- **Method**: `GET`
- **URL**: `/api/students/course/{course}`
- **Example**: `/api/students/course/Computer%20Science`
- **Response** (`200 OK`):
  ```json
  [
    {
      "name": "John Doe",
      "email": "john.doe@example.com",
      "age": 21,
      "course": "Computer Science"
    }
  ]
  ```
