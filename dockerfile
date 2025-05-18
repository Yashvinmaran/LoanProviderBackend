# Stage 1: Build the JAR using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .

# Build the app (skip tests for faster builds)
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port 9090 (as your app runs on it)
EXPOSE 9090

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
