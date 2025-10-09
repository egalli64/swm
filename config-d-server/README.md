# Configuration Server

## Build, run, check (from root directory)

- mvnw clean package -pl config-d-server
- docker build -f config-d-server/Dockerfile -t dockerized-microservices/config-server:latest .
- docker compose up config-server -d
- Check if it is among the instances currently registered with Eureka
    - http://localhost:8761/
- curl http://localhost:8888/actuator/health
- curl http://localhost:8888/actuator/info
- docker inspect config-server | grep -A 10 "Health"

## Turn off
- Stop: docker compose stop config-server
    - it can be restarted later by: docker compose start config-server
- Remove: docker compose rm config-server
