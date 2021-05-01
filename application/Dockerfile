FROM openjdk:16.0

ENV WORK_DIR /usr/telestion

EXPOSE 8080

COPY conf/config.json $WORK_DIR/config.json
COPY build/distributions/##REPO_NAME##-*.tar $WORK_DIR/

WORKDIR $WORK_DIR
ENTRYPOINT ["sh", "-c"]
CMD ["tar -xf *.tar && cd ##REPO_NAME##-* && mkdir conf && cp ../config.json conf/config.json && ./bin/##REPO_NAME##"]
