FROM openjdk:16.0

ENV WORK_DIR /usr/telestion

EXPOSE 8080

COPY conf/config.json $WORK_DIR/config.json
COPY build/distributions/telestion-project-daedalus2-*.tar $WORK_DIR/

WORKDIR $WORK_DIR
ENTRYPOINT ["sh", "-c"]
CMD ["tar -xf *.tar && cd telestion-project-daedalus2-* && mkdir conf && cp ../config.json conf/config.json && ./bin/telestion-project-daedalus2"]
