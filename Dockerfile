FROM openjdk:22-ea-19-jdk-oracle
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
