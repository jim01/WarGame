FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/libs/*.jar
COPY ${JAR_FILE} WarGame.jar
ENTRYPOINT ["java","-jar","/WarGame.jar"]
