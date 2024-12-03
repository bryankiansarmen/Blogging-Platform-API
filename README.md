---

<div align="center">

# Blogging Platform API

</div>

---

## Overview

This project is a RESTful API for managing a blogging platform, where users can create, read, update, and delete (CRUD) blog posts, as well as search for posts. The API ensures a clean separation of concerns through layered architecture and follows industry best practices for building scalable and maintainable web service.

---

## Features

- **CRUD Operations for Blog Posts:** Create, read, update, and delete blog posts.
- **Search Functionality:** Search posts by title, content, or category using wildcard search.
- **Status Code Handling:** Proper use of HTTP status codes for all operations (e.g., 201 Created, 204 No Content, 404 Not Found, 500 Internal Server Error).
- **Exception Handling:** Custom exceptions and mappers to return meaningful error messages and responses.
- **Testing:** Unit and integration tests ensure reliability, covering all critical aspects of the application.

---

## Technologies Used

- **Java 17:** Core language used to build the API.
- **Spring Boot 3.4.0:** Used for creating RESTful web service and managing API routes.
- **Tomcat:** Servlet container used to deploy and run the application.
- **MySQL:** The primary relational database for persisting blog post data.
- **H2 Database:** In-memory database used for testing.
- **Hibernate:** ORM framework for mapping Java objects to database tables.
- **JPA:** Standard API for managing relational data in Java applications.
- **JUnit & Mockito:** For unit and integration testing of the service, repository, and controller.
- **Maven:** Build automation and dependency management tool.

---

## Design and Architecture

This project follows a **layered architecture** approach with a clear separation of concerns between the controller, service, and data access layers:

**1. Controller Layer:**
- Handles HTTP requests and responses, and manages interaction with the service layer.
- Example: ``PostController.java`` for all post-related endpoints.

**2. Service Layer:**
- Implements the core business logic of the application, such as creating or updating posts, and orchestrating between the controller and repository layers.
- Example: ``PostService.java`` contains the logic for managing posts.

**3. Repository Layer:**
- Directly interacts with the database using JPA, executing SQL queries for creating, reading, updating, and deleting records.
- Example: ``PostRepository.java`` manages the persistence of post entities.

### Project Structure

```
src/
│
├── main/
│   └── java/com/example/blog/blogging_platform/
│       ├── controller/            # API controllers (e.g., PostResource.java)
│       ├── dto/                   # Data Transfer Objects (DTOs) for request and response models
│       ├── exception/             # Custom exceptions and mappers
│       ├── mapper/                # Mappers for converting entities to DTOs and vice versa
│       ├── model/                 # Entity classes (e.g., Post.java)
│       ├── repository/            # Repository for interacting with the database
│       └── service/               # Business logic and service implementations
└── test/                          # Unit and integration tests
```

---

## API Endpoints

### POST ``/api/v1/posts``
- **Description:** Create a new blog post.
- **Request:**
  - ``POST`` body must include title, content, category, and tags.
      ```json
      {
          "title": "My First Blog Post",
          "content": "This is the content of my first blog post.",
          "category": "Technology",
          "tags": ["Programming", "Tech"]
      }
      ```
- **Response:**
  - ``201 Created`` with the newly created post.
    ```json
    {
        "id": 1,
        "title": "My First Blog Post",
        "content": "This is the content of my first blog post.",
        "category": "Technology",
        "tags": ["Programming", "Tech"],
        "createdAt": "2024-10-09T12:00:00Z",
        "updatedAt": "2024-10-09T12:00:00Z"
    }
    ```
  - ```400 Bad Request``` with error messages in case of validation errors.

### GET ``/api/v1/posts/{id}``
- **Description:** Retrieve a post by its ID.
- **Response:**
  - ``200 OK`` with the post details.
    - Example: ``GET /api/posts/1``
        ```json
        {
            "id": 1,
            "title": "My First Blog Post",
            "content": "This is the content of my first blog post.",
            "category": "Technology",
            "tags": ["Programming", "Tech"],
            "createdAt": "2024-10-09T12:00:00Z",
            "updatedAt": "2024-10-09T12:00:00Z"
        }
        ```
    - ```404 Not Found```  if the blog post was not found.

### GET ``/api/v1/posts?term={term}``
- **Description:** Search posts by title, content, or category.
- **Response:**
  - ``200 OK`` with a list of matching posts or all posts if no term is provided.
    - Example: ``GET /api/v1/posts``
        ```json
        [
            {
                "id": 1,
                "title": "My First Blog Post",
                "content": "This is the content of my first blog post.",
                "category": "Technology",
                "tags": ["Programming", "Tech"],
                "createdAt": "2024-10-09T12:00:00Z",
                "updatedAt": "2024-10-09T12:00:00Z"
            },
            {
                "id": 2,
                "title": "My Second Blog Post",
                "content": "This is the content of my second blog post.",
                "category": "Technology",
                "tags": ["Programming", "Tech"],
                "createdAt": "2024-10-09T12:30:00Z",
                "updatedAt": "2024-10-09T12:30:00Z"
            }
        ]
        ```

### PUT ``/api/v1/posts/{id}``
- **Description:** Update an existing post.
- **Request:**
  - ``PUT``  body must include updated title, content, category, and tags.
    ```json
    {
        "title": "My Updated Blog Post",
        "content": "This is the updated content of my first blog post.",
        "category": "Technology",
        "tags": ["Programming", "Tech"]
    }
    ```
- **Response:**
  - ``200 OK`` with the updated blog post.
    ```json
    {
        "id": 1,
        "title": "My Updated Blog Post",
        "content": "This is the updated content of my first blog post.",
        "category": "Technology",
        "tags": ["Programming", "Tech"],
        "createdAt": "2024-10-09T12:00:00Z",
        "updatedAt": "2024-10-09T12:30:00Z"
    }
    ```
  - ``400 Bad Request`` with error messages in case of validation errors.
  - ``404 Not Found`` if the blog post was not found.

### DELETE ``/api/v1/posts/{id}``
- **Description:** Delete a post by its ID.
- **Response:**
  - ``204 No Content`` if the blog post was successfully deleted.
  - ``404 Not Found`` if the blog post was not found.

---
