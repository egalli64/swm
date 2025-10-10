# Configuration Server

Module in the monorepo [dockerized-microservices](../README.md)

## Build and run (from root directory)

- mvnw clean package -pl config-d-server
- docker build -f config-d-server/Dockerfile -t dockerized-microservices/config-server:latest .
- docker compose up config-server -d

## Check
- The log
    - docker logs config-server
- Health and info, by the actuator:
    - curl http://localhost:8888/actuator/health
    - curl http://localhost:8888/actuator/info
- Detailed information
    - docker inspect config-server
    - docker inspect --format='{{.State.Health.Status}}' config-server
- Eureka UI
    - http://localhost:8761/

- Check a service configuration
    curl http://localhost:8888/user-service/default
    - With docker profile
        curl http://localhost:8888/user-service/docker

## Turn off
- Stop
    - docker compose stop config-server
    - Restart: docker compose start config-server
- Remove
    - docker compose rm config-server
