# Dockerized Spring Microservices

Monolithic Repository (Monorepo) meant for development only

## Requirements
- Docker
    - RabbitMQ
    - PostgreSQL

## When on Windows/MacOs
- Remember to turn Docker Desktop on

## Modules
- [Eureka Server](eureka-d-server/README.md)
- [Configuration Server](config-d-server/README.md)
- [API Gateway](api-d-gateway/README.md)
- [User Service](user-d-service/README.md)
- [Product Service](product-d-service/README.md)

## Turn off
- Stop the services and Kafka: docker compose down
- Stop Docker Desktop
