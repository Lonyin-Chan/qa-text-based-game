# Stage 1: Build
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Run
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/myapp.jar /app/myapp.jar
ENTRYPOINT ["java", "-jar", "myapp.jar"]
