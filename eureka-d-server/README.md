# Eureka Server

Module in the monorepo [dockerized-microservices](../README.md)

## Build, run, check (from root directory)

- mvnw clean package -pl eureka-d-server
- docker build -f eureka-d-server/Dockerfile -t dockerized-microservices/eureka-server:latest .
- docker compose up eureka-server -d
- curl http://localhost:8761/actuator/health
- curl http://localhost:8761/actuator/info
- docker inspect eureka-server | grep -A 10 "Health"

## More
- UI: http://localhost:8761
- Registered apps: curl http://localhost:8761/eureka/apps -H "Accept: application/json"

## Turn off
- Stop: docker compose stop eureka-server
    - it can be restarted later by: docker compose start eureka-server
- Remove: docker compose rm eureka-server
