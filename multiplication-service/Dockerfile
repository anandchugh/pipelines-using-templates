
FROM maven:3-jdk-8  as builder

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package spring-boot:repackage

FROM openjdk:8-jdk-alpine

COPY --from=builder /home/app/target/multiplicationservice-0.0.1-SNAPSHOT.jar /home/app/

ENTRYPOINT ["java","-jar", "/home/app/multiplicationservice-0.0.1-SNAPSHOT.jar"]
