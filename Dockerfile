FROM maven:3.8.5-openjdk-17 as builder

WORKDIR /app

COPY . /app

RUN mvn clean install

FROM openjdk:17-alpine

COPY --from=builder /app/target/cs_task*.jar /app/app.jar

ENTRYPOINT exec java -jar /app/app.jar