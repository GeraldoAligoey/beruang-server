FROM openjdk:11.0-jdk AS builder

RUN apt-get update -qq
COPY . /beruang
WORKDIR /beruang

CMD ./mvnw clean package -Dmaven.test.skip=true

FROM gcr.io/distroless/java:11 AS beruang
COPY --from=builder /beruang/target/ /app

WORKDIR /app

ENTRYPOINT ["java", "-jar", "/app/beruang-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080