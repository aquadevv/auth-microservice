FROM gradle:8.11-jdk17 AS build

WORKDIR /app

COPY . .

RUN gradle build

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
