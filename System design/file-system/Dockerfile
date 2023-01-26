FROM maven:3-eclipse-temurin-8 as build

COPY pom.xml pom.xml
COPY src src 
RUN mvn clean install -DskipTests=true