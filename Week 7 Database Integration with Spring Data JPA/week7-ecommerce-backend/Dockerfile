# Use official OpenJDK 17 runtime image
FROM eclipse-temurin:24-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built Spring Boot JAR into the container
COPY target/week7-ecommerce-backend-0.0.1-SNAPSHOT.jar  app.jar

# Expose port 8080
EXPOSE 8080

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
