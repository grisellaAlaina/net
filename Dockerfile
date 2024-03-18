FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY target/netSpace-0.0.1-SNAPSHOT.jar netSpace.jar
ENTRYPOINT ["java","-jar","/netSpace.jar"]