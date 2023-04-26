#
# Build stage
#
#FROM maven:3.8.2-jdk-11 AS build
#COPY . .
#RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# ENV PORT=8080
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app.jar"]