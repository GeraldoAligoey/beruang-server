FROM maven:3.8.6-openjdk-11-slim AS builder
COPY . /beruang
WORKDIR /beruang
RUN mvn clean package -Dmaven.test.skip=true

FROM gcr.io/distroless/java:11 AS beruang-server
COPY --from=builder /beruang/api/target/beruang.jar /app/beruang.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/beruang.jar"]
