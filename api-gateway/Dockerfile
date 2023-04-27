#
# Build stage
#
FROM maven:3.9.1-eclipse-temurin-17-alpine AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:21-ea-17-slim-buster
COPY --from=build /target/api-gateway-1.0.jar api-gateway.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","api-gateway.jar"]