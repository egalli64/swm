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
COPY ${MODULE_DIR}/pom.xml ./${MODULE_DIR}/

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline -pl ${MODULE_DIR}

# Copy source code and build
COPY ${MODULE_DIR}/src ./${MODULE_DIR}/src
RUN ./mvnw clean package -pl ${MODULE_DIR} -DskipTests

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
