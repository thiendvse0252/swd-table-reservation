
FROM openjdk:17

WORKDIR /app

COPY ./target/swd-0.0.1-SNAPSHOT.jar /app

EXPOSE 8081

CMD ["java", "-jar", "swd-0.0.1-SNAPSHOT.jar"]
