FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar notification-service.jar
ENTRYPOINT ["java", "-jar", "notification-service.jar"]
