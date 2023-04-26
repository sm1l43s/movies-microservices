#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /target/movies-service-1.0.jar movies-service.jar
# ENV PORT=8080
#EXPOSE 8080
ENTRYPOINT ["java","-jar","movies-service.jar"]