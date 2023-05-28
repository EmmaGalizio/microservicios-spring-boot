FROM openjdk:17-jdk-alpine

WORKDIR /movie-ratings-service

COPY ./target/movie-ratings-service-0.0.1-SNAPSHOT.jar .


ENTRYPOINT ["java","-jar","movie-ratings-service-0.0.1-SNAPSHOT.jar"]