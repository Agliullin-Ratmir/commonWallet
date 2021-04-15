FROM openjdk:11
EXPOSE 8080:8080
EXPOSE 8080
ADD /build/libs/first-0.0.1-SNAPSHOT.jar first.jar
ENTRYPOINT ["java", "-jar", "first.jar"]