FROM amazoncorretto:19
MAINTAINER gastonAlonso
COPY target/LGA-0.0.1-SNAPSHOT.jar LGA.jar
ENTRYPOINT ["java","-jar","/LGA.jar"]