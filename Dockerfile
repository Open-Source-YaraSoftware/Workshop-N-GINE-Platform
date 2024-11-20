# Etapa 1: Construir la aplicaciÃ³n
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B  # Descarga las dependencias sin compilar
COPY src ./src
RUN mvn clean package -DskipTests  # Compila el proyecto

# Etapa 2: Crear la imagen final
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/platform-0.0.1-SNAPSHOT.jar /app/platform-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "platform-0.0.1-SNAPSHOT.jar"]