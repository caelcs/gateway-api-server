# Gateway API Server

## Overview
Rest Application that provides access to endpoints that comunicate with others apps.
 
## Requierments
- Docker 1.7
- Java

## Build and Run docker image

In order to build and image use:
- ./gradlew buildDocker

if you want to push it
- docker push adolfoecs/gateway-api-server:0.1.0-SNAPSHOT

In order to run the image use:

- docker run -p 8080 --name gateway-api-server-instance1 -t adolfoecs/gateway-api-server:0.1.0-SNAPSHOT