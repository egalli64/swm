# syntax=docker/dockerfile:1.4
# BuildKit

# Spring Boot Docker Microservices - Eureka Server
#
# https://github.com/egalli64/dockerized-microservices

# 
# Build stage
#
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /app

# Accept build argument for module directory name
ARG MODULE_DIR=eureka-d-server

# Copy Maven wrapper and pom files
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Copy all module pom.xml files (Maven reactor needs to see all modules)
COPY eureka-d-server/pom.xml ./eureka-d-server/
COPY config-d-server/pom.xml ./config-d-server/
COPY api-d-gateway/pom.xml ./api-d-gateway/
COPY user-d-service/pom.xml ./user-d-service/
COPY product-d-service/pom.xml ./product-d-service/

# Download dependencies (uses a persistent cache for /root/.m2) w/ BuildKit
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw dependency:go-offline -pl ${MODULE_DIR}

# Copy source code and build
COPY ${MODULE_DIR}/src ./${MODULE_DIR}/src
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw clean package -pl ${MODULE_DIR} -DskipTests

#
# Runtime stage
#
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Accept build argument for module directory name
ARG MODULE_DIR=eureka-d-server

# Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy jar from builder stage
COPY --from=builder /app/${MODULE_DIR}/target/*.jar app.jar

# Expose port
EXPOSE 8761

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8761/actuator/health || exit 1

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
