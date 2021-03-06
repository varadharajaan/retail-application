﻿# retail-store-spring-boot

## Swagger API Specs

Swagger API documentation specs are auto-generated when the server runs. The specs will be published under the URL http://localhost:8080/swagger-ui.html

## Installing

After checking out the git repo run the following maven command

```bash
mvn install
```

This should install all packages, run all unit tests and exit successfully

## Starting

To start the server please run the following maven command

```bash
mvn spring-boot:run
```


## Docker

```bash
mvn clean
mvn package docker:build
mvn docker:run
```

## Testing

To execute the unit tests against the business logic service classes please run the following maven command

```bash
mvn test
```

##Implementations:

1. any number of discount slabs can be added in enum  class and the same can be added to database.
2. used global exception and global logging
3. added few funcationlity like bill discount/ grocery and non grocery discount and as of now the code is commented in discountengine.
4. dockerized the application so that easy to deploy
5. async config as been added to main reduce thread usage
6. future enchancement can be done in taking much more sprints
7. production ready deployable code.
8. request and response given below. can  use the application for regular and premium customer with N number of items in the bill.


##ACTUTATOR

actuator has been implemented to check the health and core process of the application and the future scope can be given to any UI tool like griffana

##REQUEST:

{
  "bill": {
    "items": [
      {
        "price": 5000
      },
      {
        "price": 10000
      },
      {
        "price": 15000
      }
    ]
  },
  "user": {
    "type": "REGULAR"
  }
}

##RESPONSE

{
  "5000.0": 5000,
  "10000.0": 9000,
  "15000.0": 12000
}
##DEPLOYMENT

Application already dockerized and deployed in Herouku cloud.
##https://retailstoreapp.herokuapp.com/swagger-ui.html

just send request in above format to get the response of discounted bill amount

