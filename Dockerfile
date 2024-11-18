# Используем базовый образ с установленной Java
FROM openjdk:17-jdk

RUN microdnf install findutils
RUN apk update && apk add findutils

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем скомпилированный JAR файл в образ
COPY build/libs/app.jar app.jar

# Указываем, что приложение будет слушать на порту 8080
EXPOSE 8088

# Устанавливаем точку входа для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
