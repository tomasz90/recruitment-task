# Backend Recruitment Task

## Project Description

This is a simple RESTful service that provides information about GitHub users. It retrieves data from the GitHub API, including:

- User ID
- Login
- Name
- Type
- Avatar URL
- Creation Date
- Some Calculations

The API of the service follows the structure below:

### GET /users/{login}

responses with:

```json
{
  "id": "...",
  "login": "...",
  "name": "...",
  "type": "...",
  "avatarUrl": "...",
  "createdAt": "...",
  "calculations": "..."
}
```

The "calculations" field contains the result of the calculation: `6 / followers_count * (2 + public_repos_count)`.

Additionally, the service keeps track of the number of API requests made for each user's login. This information is stored in a database with two columns: `LOGIN` and `REQUEST_COUNT`. The `REQUEST_COUNT` is incremented for each API call.

You can test this service using Swagger UI available on: http://localhost:8080/swagger-ui/index.html

Repo also contains github actions which triggers all tests on push to master

## Technology Stack

- Main code implemented in Java
- Tests implemented in Groovy using Spock + WireMock
- Buildable using Gradle

Please ensure that good programming practices are followed during development.