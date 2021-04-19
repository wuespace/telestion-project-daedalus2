FROM openjdk:14-alpine

ENV WORK_DIR /usr/telestion

EXPOSE 8080

COPY conf/config.json $WORK_DIR/config.json
COPY build/distributions/Daedalus2GroundStation-*.tar $WORK_DIR/

WORKDIR $WORK_DIR
ENTRYPOINT ["sh", "-c"]
CMD ["tar -xf *.tar && cd Daedalus2GroundStation-* && mkdir conf && cp ../config.json conf/config.json && ./bin/Daedalus2GroundStation"]
