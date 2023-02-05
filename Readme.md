# springboot-irrigation-app

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal implementation for irrigation system 
## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)
- [MySQL](https://www.mysql.com/)

## Database Initialization & Seed Configuration

* The db schema is found in "/src/main/resources/schema.sql" 
* Default seed data in "/src/main/resources/data.sql" 

## Sensor Related Configuration
* Configuration related to sensor retry & timeout are stored in the configuration in (src/main/resources/sensor.retry.config.properties)



## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run