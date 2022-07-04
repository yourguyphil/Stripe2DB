FROM openjdk:17

RUN mkdir /app

COPY stripe2db/* /app

WORKDIR /app

CMD java Main