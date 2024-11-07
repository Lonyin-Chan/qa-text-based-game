FROM openjdk:17

WORKDIR /app
COPY text-based-game-1.0-SNAPSHOT.jar /app/

CMD ["java", "-jar", "/app/text-based-game-1.0-SNAPSHOT.jar"]
