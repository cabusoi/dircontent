FROM openjdk:8-jre

VOLUME /tmp

COPY ./target/sorter-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
