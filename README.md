# seerbit

Seerbit Transactions

Steps to run:

Build image from docker file e.g.

docker image build -t seerbit:salihu .

Tag image to latest

docker image tag seerbit:salihu seerbit:latest

Run container

docker container run --name test -d -p 8080:8080 seerbit

Test url from browser:

localhost:8080/transaction
