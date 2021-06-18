FROM openjdk:8-jdk-alpine

RUN apk add --no-cache git

RUN git status

RUN mkdir -p /services/demo-linkaja

COPY src /services/demo-linkaja/src

COPY pom.xml /services/demo-linkaja/pom.xml

WORKDIR /services/demo-linkaja

RUN mvn compile

ENV TZ Asia/Jakarta

EXPOSE 8080

ENTRYPOINT ["/sbin/tini", "--", "sh", "-c", "java -jar target/demo-linkaja-0.0.1.jar --server.port=8080 --app.rest-read-timeout=50000"]
