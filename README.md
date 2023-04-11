# REST backend authentication with JWT service

This server provide registration and login with username, email and password(which will be encoded) like user with JWT token.
Also, you can create posts with title, content and image files.

## Install
### 1) Clone
    git clone https://github.com/lxczzdd/rest-backend.git
    cd rest-backend

## Build and deploy
### 1) Docker
    docker-compose up
    
### 2) Manual
You can build the JAR file with

    ./mvnw clean package
and then run the JAR file, as follows:

    java -jar target/app-0.0.1-SNAPSHOT.jar
    
## API
Swagger docs - https://lxczzdd.github.io/rest-backend/
