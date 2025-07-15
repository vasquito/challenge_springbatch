# Container 
FROM openjdk:17

WORKDIR /app
COPY build/libs/acc-batch.jar batch.jar
CMD ["java", "-jar", "batch.jar"]