# syntax=docker/dockerfile:1
FROM sbtscala/scala-sbt:eclipse-temurin-jammy-19.0.1_10_1.8.2_3.2.1
ENV DISPLAY=host.docker.internal:0.0
USER root
WORKDIR /uno
COPY . /uno
RUN apt-get update && apt-get install -y \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/* \
    && sbt compile 
CMD sbt run