# Travel Adventures API

A RESTful API for logging and managing travel adventures, built with Spring Boot. This project was completed as part of the [Codecademy Learn Spring](https://www.codecademy.com/learn/learn-spring) course. The final hands-on exercise after working through the Spring Controllers section.

## What It Does

The API functions as a travel log backend. It allows you to:

- Retrieve all adventures or filter by country or state
- Add new travel entries
- Update an existing entry's blog completion status
- Delete entries by ID

There is no frontend — it's a pure REST API intended to be consumed by a client (browser, curl, a frontend framework, etc.).

## Tech Stack

- Java 25
- Spring Boot 4
- Spring Data JPA
- H2 in-memory database

## Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/traveladventures` | Get all adventures |
| GET | `/traveladventures/bycountry/{country}` | Get adventures by country |
| GET | `/traveladventures/bystate?state={state}` | Get adventures by state |
| POST | `/traveladventures` | Create a new adventure (returns 201) |
| PUT | `/traveladventures/{id}` | Update an adventure's blog status (returns 404 if not found) |
| DELETE | `/traveladventures/{id}` | Delete an adventure (returns 204) |

## Running the Project

```bash
./mvnw spring-boot:run
```

The server starts on port 4001.

## What I Learned

This was my first real backend engineering project and my introduction to Spring Boot. Key concepts I worked through:

**Spring Boot & REST**
- Building a REST API using `@RestController`, `@RequestMapping`, and HTTP method annotations (`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`)
- The difference between `@PathVariable`, `@RequestParam`, and `@RequestBody` — where each piece of data lives in an HTTP request and when to use each
- HTTP status codes as part of the API contract: 201 for resource creation, 404 for not found, 204 for deletion — and how `@ResponseStatus` overrides Spring's default 200

**Spring Data JPA**
- How `CrudRepository` provides `findAll`, `save`, `delete`, and `findById` without any implementation
- How Spring Data JPA derives query implementations from method names (`findByCountry`, `findByState`) by inspecting the entity's fields at startup — no SQL written, no implementation class needed
- The JPA managed vs. detached entity distinction: changes to a detached entity (one returned outside an active transaction) do not persist to the database — you must explicitly call `save()` after modifying it

**Debugging Spring Boot**
- Reading startup logs to diagnose failures — Java version mismatches, SQL script ordering errors, and table-not-found exceptions each leave a distinct trace
- The `spring.jpa.defer-datasource-initialization=true` property, which is required in Spring Boot 2.5+ when combining Hibernate DDL auto-generation with a `data.sql` seed file
- Using `curl -v` to inspect HTTP status codes and response bodies when testing endpoints without a frontend
