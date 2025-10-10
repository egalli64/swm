# API Gateway

Module in the monorepo [dockerized-microservices](../README.md)

## Build and run (from root directory)

- mvnw clean package -pl api-d-gateway
- docker build -f api-d-gateway/Dockerfile -t dockerized-microservices/api-gateway:latest .
- docker compose up api-gateway -d

## Check
- The log
    - docker logs api-gateway
- Health and info, by the actuator:
    - curl http://localhost:8080/actuator/health
    - curl http://localhost:8080/actuator/info
- Detailed information
    - docker inspect api-gateway
    - docker inspect --format='{{.State.Health.Status}}' api-gateway
- Eureka UI
    - http://localhost:8761/
    
## Turn off
- Stop
    - docker compose stop api-gateway
    - Restart: docker compose start api-gateway
- Remove
    - docker compose rm api-gateway
