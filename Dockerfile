# Step 1: Build Stage
FROM maven:3.8.3-openjdk-17 AS build 

# Set the working directory in the container
WORKDIR /app

# Copy necessary files
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Step 2: Run Stage
FROM eclipse-temurin:17-jdk-alpine 

# Set the working directory in the container
WORKDIR /app
VOLUME /tmp

# Copy the JAR file from the build stage
COPY --from=build /app/target/x-gains-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]