FROM openjdk:14-alpine

ENV WORK_DIR /usr/telestion

EXPOSE 8080

COPY conf/config.json $WORK_DIR/config.json
COPY build/distributions/TelestionDaedalus2Client-*.tar $WORK_DIR/

WORKDIR $WORK_DIR
ENTRYPOINT ["sh", "-c"]
CMD ["tar -xf *.tar && cd TelestionDaedalus2Client-* && mkdir conf && cp ../config.json conf/config.json && ./bin/TelestionDaedalus2Client"]
