FROM openjdk:8-jdk-alpine
COPY /target/eCommerceP2-0.0.1-SNAPSHOT.jar eCommerceP2-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/eCommerceP2-0.0.1-SNAPSHOT.jar"]