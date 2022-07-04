FROM openjdk:17
ARG JAR_FILE=stripe2db/stripe2db/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]