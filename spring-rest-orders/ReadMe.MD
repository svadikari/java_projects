## Spring Boot REST API Demo 

This multi-module project build to demonstrate REST APIs using Java, spring boot combination.


### What are capabilities covered?
    - Web REST APIs (Spring Web)
    - Data REST APIs (Spring Data REST)
    - Unit Tests (Jupiter)
    - Performance Tests (Gatling - Java DSL)
    - Backend (MongoDB)
    - Mapping Framework (Mapstruct)
    - Toggle Feature (Togglz)
    - Distributed Tracing (Micrometer)

### Feature toggle URL
    - http://127.0.0.1:8081/shyam/togglz-console/index

### How to run gatling test?
    - ./gradlew clean gatlingRun ## execute all simulations
    - ./gradlew clean gatlingRun-com.shyam.orders.OrderSimulation ## execute specific simulator
