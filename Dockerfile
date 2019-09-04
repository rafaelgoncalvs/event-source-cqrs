FROM openjdk:8-alpine
RUN mkdir -p /app
COPY ./target/event-source-cqrs-1.0.0.jar /app
CMD ["java", "-jar", "./app/event-source-cqrs-1.0.0.jar"]
EXPOSE 8080
