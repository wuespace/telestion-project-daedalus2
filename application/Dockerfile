FROM openjdk:16.0

ENV APP_DIR /usr/telestion/telestion-project-daedalus2

ADD build/distributions/telestion-project-daedalus2.tar /usr/telestion

WORKDIR $APP_DIR
EXPOSE 9870
EXPOSE 9871

ENTRYPOINT ["sh", "-c"]
CMD ["./bin/telestion-project-daedalus2"]
