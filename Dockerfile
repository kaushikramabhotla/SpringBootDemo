FROM openjdk:21-jdk
ADD target/demo-archive-file.jar demo-archive-file.jar
ENTRYPOINT ["java", "-jar", "/demo-archive-file.jar"]