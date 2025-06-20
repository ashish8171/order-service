# Order Service
## This service is responsible for creating and retrieving an order. 

This service creates an order and stores in the DB of your choice which can be configured using the yml/properties file for DB of your choice.
## Features

- Create an order with order amount in USD
- Retrieves and order by providing the country for which you want the amount to be converted.

## Create Order
**Method:** POST
**Headers:** X-TRACE-ID: unique Identifier for tracing the request
**URL:** <host>:<port>/api/v1/purchase/order/createOrder
**BODY:**
```json
{
   "description": "1000000",
   "amount" : 10, 
   "transactionDate" :"2025-08-08"
}
```
**Response:**
**HTTPCode:** 201 created
**BODY:**
```json
{"orders": [{
   "orderId": 1,
   "description": "1000000",
   "amount": 10,
   "transactionDate": "2025-08-08"
}]}
```

## Retrieve Order
**Method:** GET
**Headers:** X-TRACE-ID: unique Identifier for tracing the request
**URL:** <host>:<port>//api/v1/purchase/order/<orderId>/<Country>
**Response:**
```json
{
   "orderId": 1,
   "description": "1000000",
   "amount": 10, // Original purchase price in USD
   "transactionDate": "2025-08-08",
   "exchangeRate": 70.86, // Exchange rate for the specified country
   "convertedAmount": 708.6 // amount converted in the specified country currency
}
```
## How to clone and run project
Use git clone or clone the repo from your preferred IDE
Once clone, build the project using your preferred IDE or command prompt
**Requirement**: JDK 21 and Maven latest veresion

**Building the project**
```shell
cd <location of the project cloned> mvn clean install
```
**Running the Project**
```shell
<location of the project cloned> java -jar target/order-service-0.0.1-SNAPSHOT.jar
```
The service will be up and running and then you can use the attached **(src/test/resources)** SoapUI project to test the service
