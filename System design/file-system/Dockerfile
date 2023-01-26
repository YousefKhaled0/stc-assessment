FROM maven:3.8-eclipse-temurin-8 as build
WORKDIR /app
COPY pom.xml pom.xml
COPY src src
RUN mvn clean install

FROM openjdk:8-alpine
EXPOSE 8080
WORKDIR /app
COPY --from=build /app/target/file-system-0.0.1-SNAPSHOT.jar file-system-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","file-system-0.0.1-SNAPSHOT.jar"]
