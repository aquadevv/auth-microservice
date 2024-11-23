FROM gradle:8.11-jdk17 AS build

WORKDIR /app

COPY build.gradle settings.gradle /app/

RUN gradle build --no-daemon

FROM openjdk:17-jre-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
