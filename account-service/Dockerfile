FROM  openjdk:28-ea-jdk-slim
LABEL author="mahir"
COPY target/accounts-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java","-jar","/app.jar" ]