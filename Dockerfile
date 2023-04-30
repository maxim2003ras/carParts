#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER maksim

# Add the application's jar to the container
COPY target/carParts-0.0.1-SNAPSHOT.jar carParts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/carParts-0.0.1-SNAPSHOT.jar"]