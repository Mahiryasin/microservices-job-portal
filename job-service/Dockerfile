FROM maven:3.9-amazoncorretto-21-alpine AS builder

WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn clean install -DskipTests


FROM bellsoft/liberica-openjdk-alpine:23


COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8016
ENTRYPOINT [ "java","-jar","app.jar" ]


# / bu ana  dizin demek !