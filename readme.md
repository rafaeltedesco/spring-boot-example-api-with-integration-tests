# Example of API with Spring Boot + Integration Tests

---

### Run DB with Docker 🐳

```bash
  docker compose up -d
```

---

### Run Tests with Maven

```bash
mvn test
```

### Run application with Maven

```bash
mvn spring-boot:run
```

### Endpoints

- Find User By Id
  ```/GET /api/users/{id}```

- Create User
  ```/POST /api/users```
  
  Expected Payload:
  ```json
    { 
      "name": "John Doe",
      "email": "johndoe@test.com"
    }
  ```