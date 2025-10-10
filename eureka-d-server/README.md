# Eureka Server

Module in the monorepo [dockerized-microservices](../README.md)

## Build and run (from root directory)

- mvnw clean package -pl eureka-d-server
- docker build -f eureka-d-server/Dockerfile -t dockerized-microservices/eureka-server:latest .
- docker compose up eureka-server -d

## Check
- The log
    - docker logs eureka-server
- Health and info, by the actuator
    - curl http://localhost:8761/actuator/health
    - curl http://localhost:8761/actuator/info
- Detailed information
    - docker inspect eureka-server
    - docker inspect --format='{{.State.Health.Status}}' eureka-server
- UI
    - http://localhost:8761
- Registered apps
    - curl http://localhost:8761/eureka/apps -H "Accept: application/json"

## Turn off
- Stop
    - docker compose stop eureka-server
    - Restart: docker compose start eureka-server
- Remove
    - docker compose rm eureka-server
