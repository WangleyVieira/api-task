# Task API

A REST API for managing tasks, built with Java 21, Spring Boot, Spring Data JPA, and PostgreSQL.

## Features

- Create, retrieve, update, and delete tasks
- Persist tasks in PostgreSQL
- Validate task titles
- Return structured errors for invalid requests and tasks that are not found

## Requirements

- Java 21
- PostgreSQL
- Maven (or the included Maven Wrapper)

## Getting Started

1. Create a PostgreSQL database named `task_db`.
2. Configure the database connection in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/task_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

   Keep credentials private and do not commit them to version control.

3. Start the application from the project root:

   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows, use:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

The API is available at `http://localhost:8080`.

## API Endpoints

| Method | Endpoint | Description | Success status |
|--------|----------|-------------|----------------|
| `GET` | `/api/tasks` | List all tasks | `200 OK` |
| `GET` | `/api/tasks/{id}` | Get a task by ID | `200 OK` |
| `POST` | `/api/tasks` | Create a task | `201 Created` |
| `PUT` | `/api/tasks/{id}` | Update a task | `200 OK` |
| `DELETE` | `/api/tasks/{id}` | Delete a task | `204 No Content` |

### Create a task

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Write documentation","completed":false}'
```

### Update a task

```bash
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Review documentation","completed":true}'
```

### Example task response

```json
{
  "id": 1,
  "title": "Write documentation",
  "completed": false
}
```

## Error Responses

Task titles must not be blank. Invalid requests return `400 Bad Request`; requests for a task that does not exist return `404 Not Found`.

```json
{
  "status": 404,
  "erro": "Not Found",
  "messages": [
    "Task not found with id: 99"
  ]
}
```

## Running Tests

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```
