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
COPY --from=build /target/discovery-service-1.0.jar discovery-service.jar
# ENV PORT=8761
EXPOSE 8761
ENTRYPOINT ["java","-jar","discovery-service.jar"]