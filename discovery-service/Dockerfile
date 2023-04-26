#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim
COPY --from=build /target/discovery-service-1.0.jar discovery-service.jar
# ENV PORT=8080
EXPOSE 8761
ENTRYPOINT ["java","-jar","discovery-service.jar"]