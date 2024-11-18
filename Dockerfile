# Используем базовый образ с Gradle
FROM gradle:7.5.1-jdk17 AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта
COPY . .

# Собираем проект
RUN gradle build --no-daemon

# Используем базовый образ Java для запуска приложения
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем скомпилированный JAR файл из предыдущего образа
COPY --from=build /app/build/libs/*.jar app.jar

# Указываем, что приложение будет слушать на порту 8088
EXPOSE 8088

# Устанавливаем точку входа для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]