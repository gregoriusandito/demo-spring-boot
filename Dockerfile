FROM openjdk:8-jdk-alpine

RUN apk add --no-cache git maven tini

RUN export PATH=${PATH}:${JAVA_HOME}/bin

RUN mkdir -p /services/demo-linkaja

WORKDIR /services/demo-linkaja/

RUN git clone https://github.com/gregoriusandito/demo-spring-boot.git

WORKDIR /services/demo-linkaja/demo-spring-boot

RUN mvn package

ENV TZ Asia/Jakarta

EXPOSE 8080

ENTRYPOINT ["/sbin/tini", "--", "sh", "-c", "java -jar target/demo-linkaja-0.0.1-SNAPSHOT.jar --server.port=8080 --app.rest-read-timeout=50000"]