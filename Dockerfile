FROM amazoncorretto:17-alpine-jdk
MAINTAINER VAM
COPY target/mgb-0.0.1-SNAPSHOT.jar vap-app.jar
entrypoint ["java","-jar","/vap-app.jar"]
