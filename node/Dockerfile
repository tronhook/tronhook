FROM openjdk:8-alpine

ADD ./scripts/run.sh /run.sh
ADD ./target/node-0.0.1-SNAPSHOT.jar /app.jar

RUN chmod u+x /run.sh

CMD ["sh","/run.sh"]