# Product Service

Module in the monorepo [dockerized-microservices](../README.md)

## Build and run (from root directory)

- mvnw clean package -pl product-d-service
- docker build -f product-d-service/Dockerfile -t dockerized-microservices/product-service:latest .
- docker compose up product-service -d

## Check
- The log
    - docker logs product-service
- Health and info, by the actuator:
    - curl http://localhost:8082/actuator/health
    - curl http://localhost:8082/actuator/info
- Detailed information
    - docker inspect product-service
    - docker inspect --format='{{.State.Health.Status}}' product-service
- Eureka UI
    - http://localhost:8761/
    
## Turn off
- Stop
    - docker compose stop product-service
    - Restart: docker compose start product-service
- Remove
    - docker compose rm product-service
