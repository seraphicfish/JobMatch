# ![pageres](media/dxpLogo.png)

# JobMatchApplication

## Overview

JobMatchApplication provides Restful APIs to return the most matched jobs for the users;

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

The following libraries and tools should be installed before setup the project:

- Java SE Development Kit: [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

_Optional_
- Build tool: [Gradle 6.9.1](https://gradle.org/releases/)

### Installing

The content below provide a step-by-step instructions to get the development environment running.

- Download and install an IDE for spingboot project. You can choose any Java IDE for code development, but **IntelliJ** is the recommended IDE.
- Clone the repository and import this project as a **Gradle** project into the IDE.
- Configure the project with proper JDK version.
- Configure `application.properties` file with correct configuration for 

- Server port and context setting

```
# ===================================
#  = Application Configuration
# ===================================
# The port number that this server will be deployed onto.
server.port=8080
# The context path of the application
server.servlet.context-path=/

```

- Compile the project, run the following command in the terminal
```
gradlew compileJava
```
- Run the application, run the following command in the terminal
```
gradlew bootRun
```

## Testing

The test cases(due to the limit time, the test cases are not with high coverage yet) are provided in the project. When the service is up, the test cases can be run.

Or when the service is up, using the postman to do the testing. The default url for the service is as below:
http://localhost:8080/matchedJobs/1 (the port is configurable in the application.properties and the '1' is the userId which can
be changed based on what userId need to be tested.)

In the postman, using the 'GET' method to call the above url, the most matched three jobs will be returned.
