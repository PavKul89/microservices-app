FROM maven:3.9.4-eclipse-temurin-17 as builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar order-service.jar

ENTRYPOINT ["java", "-jar", "order-service.jar"]
