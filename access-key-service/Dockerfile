#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:17.0.2-jdk
COPY --from=build /target/access-key-service-1.0.jar access-key-service.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","access-key-service.jar"]