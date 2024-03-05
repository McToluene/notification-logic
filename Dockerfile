FROM openjdk:17-oracle
VOLUME /tmp
COPY build/libs/notification-logic-service-0.0.1-SNAPSHOT.jar notification-system-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "notification-system-service.jar"]