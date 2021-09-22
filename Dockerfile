FROM openjdk:jdk-alpine

COPY target/transactions-0.0.1-SNAPSHOT.jar /deployments/

CMD java -jar /deployments/transactions-0.0.1-SNAPSHOT.jar