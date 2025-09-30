# Dormitory Management System API

This project is a comprehensive Spring Boot REST API for managing a student dormitory. It handles everything from student and room management to maintenance requests, payments, and personnel administration. The system is designed to be run easily and consistently across different environments using Docker.

## Table of Contents

  - [Features](https://www.google.com/search?q=%23features)
  - [Technologies Used](https://www.google.com/search?q=%23technologies-used)
  - [Getting Started](https://www.google.com/search?q=%23getting-started)
  - [API Endpoints](https://www.google.com/search?q=%23api-endpoints)
  - [Database Schema](https://www.google.com/search?q=%23database-schema)

## Features

  - **Student Management**: CRUD operations for students, including assigning them to rooms and managing their personal information.
  - **Room Management**: CRUD operations for rooms, including capacity and floor details.
  - **Personnel Management**: Manage dormitory staff and their roles.
  - **Maintenance Ticketing**: Students can report maintenance issues, and staff can track and update the status of these tickets.
  - **Payment Tracking**: Log and manage student payments.
  - **Waiting List**: If a room is full, students can be added to a waiting list for that room.
  - **Statistics**: Get a count of students by their academic department.
  - **Activity Log**: Tracks activities for each student.
  - **System Settings**: Includes a maintenance mode to disable certain operations.

## Technologies Used

  - **Backend**: Spring Boot
  - **Database**: MySQL
  - **Authentication**: Spring Security (with basic authentication)
  - **API Documentation**: Swagger/OpenAPI
  - **Containerization**: Docker and Docker Compose
  - **Build Tool**: Maven

## Getting Started

### Prerequisites

  - You must have **Docker** and **Docker Compose** installed on your machine.

### How to Run the Application

1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/orkunkoraytaner/Dorm_Management_BE.git
    ```
2.  **Navigate to the Project Directory**:
    ```bash
    cd Dorm_Management_BE
    ```
3.  **Run with Docker Compose**:
    Execute the following command to build the Spring Boot application and start both the application and the MySQL database containers.
    ```bash
    docker-compose up --build
    ```

The API will be available at `http://localhost:8080`.

### Stopping the Application

  - To stop the running containers, press `Ctrl + C` in the terminal where `docker-compose` is running.
  - To stop the containers and remove them, run:
    ```bash
    docker-compose down
    ```

## API Endpoints

The following are the main API endpoints available. For a complete and interactive API documentation, run the application and navigate to:

  - **Swagger UI**: `http://localhost:8080/swagger-ui.html`

### Student Endpoints

  - `POST /students`: Create a new student.
  - `GET /students`: Get a list of all students.
  - `GET /students/{id}`: Get a student by their ID.
  - `PUT /students/{id}`: Update a student's information.
  - `DELETE /students/{id}`: Delete a student.
  - `POST /students/{studentId}/assign-room/{roomId}`: Assign a student to a room.
  - `GET /students/statistics/by-department`: Get the count of students by department.

### Room Endpoints

  - `POST /rooms`: Create a new room.
  - `GET /rooms`: Get a list of all rooms.
  - `GET /rooms/{id}`: Get a room by its ID.
  - `PUT /rooms/{id}`: Update a room's details.

### Payment Endpoints

  - `GET /students/{studentId}/payments`: Get all payments for a specific student.
  - `POST /students/{studentId}/payments`: Add a new payment for a student.
  - `DELETE /students/{studentId}/payments/{paymentId}`: Delete a payment.

### Maintenance Ticket Endpoints

  - `POST /rooms/{studentId}/tickets`: Create a new maintenance ticket.
  - `GET /rooms/{roomId}/tickets`: Get all tickets for a specific room.
  - `PUT /rooms/{roomId}/tickets/{ticketId}/status`: Update the status of a ticket.
  - `DELETE /rooms/{roomId}/tickets/{ticketId}`: Delete a ticket.

### Waiting List Endpoints

  - `POST /rooms/{roomId}/waiting-list`: Add a student to a room's waiting list.
  - `GET /rooms/{roomId}/waiting-list`: Get the waiting list for a specific room.
  - `POST /rooms/{roomId}/waiting-list/next`: Get the next student from the waiting list.

## Database Schema

The application uses a relational database with the following main entities:

  - **`Student`**: Represents a student in the dormitory. It has a many-to-one relationship with the `Room` entity.
  - **`Room`**: Represents a room in the dormitory. It has one-to-many relationships with `Student`, `MaintenanceTicket`, and `WaitingListEntry`.
  - **`Personal`**: Represents a staff member in the dormitory.
  - **`Payment`**: Represents a payment made by a student.
  - **`MaintenanceTicket`**: Represents a maintenance request for a room.
  - **`WaitingListEntry`**: Represents a student's entry in a room's waiting list.
