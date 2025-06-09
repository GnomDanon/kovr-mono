FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/platform-mono-1.0.0.jar /app/platform.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/platform.jar"]