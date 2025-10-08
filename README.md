# Dockerized Spring Microservices

Monolithic Repository (Monorepo) meant for development only

## Requirements
- Docker (Start Docker Desktop if on Windows/MacOS)
    - RabbitMQ
    - PostgreSQL

## Modules
- Eureka server - Discovery

## Build, run, check a service (from root directory)
- mvnw clean package -pl eureka-d-server
- docker build -f eureka-d-server/Dockerfile -t dockerized-microservices/eureka-server:latest .
- docker compose up eureka-server -d
- curl http://localhost:8761/actuator/health
- curl http://localhost:8761/actuator/info
- docker inspect eureka-server | grep -A 10 "Health"

## Eureka specific
- UI: http://localhost:8761
- Registered apps: curl http://localhost:8761/eureka/apps -H "Accept: application/json"

## Shutdown
- Stop the services and Kafka: docker compose down
- Stop Docker Desktop
