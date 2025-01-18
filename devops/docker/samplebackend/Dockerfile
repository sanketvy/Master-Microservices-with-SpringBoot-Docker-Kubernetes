FROM openjdk:21-jdk-slim

WORKDIR /sample

COPY ./target/samplebackend-0.0.1-SNAPSHOT.jar .

EXPOSE 9000

CMD ["java","-jar", "samplebackend-0.0.1-SNAPSHOT.jar"]