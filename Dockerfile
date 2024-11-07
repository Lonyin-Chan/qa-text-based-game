# Stage 1: Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Run stage
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/myapp.jar /app/myapp.jar
ENTRYPOINT ["java", "-jar", "myapp.jar"]
