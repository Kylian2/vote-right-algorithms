FROM maven:3.9.9-eclipse-temurin-23 AS builder
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:23-jdk-alpine
WORKDIR .
COPY --from=builder target/*.jar app.jar

EXPOSE 3334

CMD ["java", "-jar", "app.jar"]