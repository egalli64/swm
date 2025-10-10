# User Service

Module in the monorepo [dockerized-microservices](../README.md)

## Build and run (from root directory)

- mvnw clean package -pl user-d-service
- docker build -f user-d-service/Dockerfile -t dockerized-microservices/user-service:latest .
- docker compose up user-service -d

## Check
- The log
    - docker logs user-service
- Health and info, by the actuator:
    - curl http://localhost:8081/actuator/health
    - curl http://localhost:8081/actuator/info
- Detailed information
    - docker inspect user-service
    - docker inspect --format='{{.State.Health.Status}}' user-service
- Eureka UI
    - http://localhost:8761/
    
## Turn off
- Stop
    - docker compose stop user-service
    - Restart: docker compose start user-service
- Remove
    - docker compose rm user-service
