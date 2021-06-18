# Car Lease API Gateway

This gateway is the system entrypoint and has the responsiblity to authenticate and issue JWT's and route to services
It's very basic and not using spring security The service
uses [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)

## Functionality

Routing to CarService and CustomerService and authentication

### URL Mapping

#### AUTHENTICATION
- [x] POST:   {domain}/authenticate - authenticate using username and password to obtain a JWT  

#### CARS
- [x] GET:    {domain}/cars             - returns al cars
- [x] GET:    {domain}/cars/{id}        - returns specific cars
- [x] POST    {domain}/cars             - create new cars
- [x] DELETE: {domain}/cars{id}         - delete specific cars
- [x] PUT:    {domain}/cars{id}         - update specific cars
- [x] GET:    {domain}/cars{id}/leaserate?{mileage,interest,duration} - calculculates leaserate for given car. required parameters

#### CUSTOMERS
- [x] GET:    {domain}/customers          - returns al customers
- [x] GET:    {domain}/customers/{id}     - returns specific customer
- [x] POST    {domain}/customers          - create new customer
- [x] DELETE: {domain}/customers/{id}     - delete specific customer
- [x] PUT:    {domain}/customers/{id}     - update specific customer

## Run application

This service runs on port 9092

In development use dev profile:

```shell
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## get in

Using the most secure overused password in the world.

```
admin:Welkom01
broker:Welkom01
```

```
POST {domain}/authenticate - authentication endpoint see below example 
```

POST authenticate example

```json
{
  "username": "admin",
  "password": "Welkom01"
}
```
