FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER diegocairone@gmail.com
RUN apk update
RUN apk add imagemagick
ARG JAR_FILE=target/poc-imagemagick-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
