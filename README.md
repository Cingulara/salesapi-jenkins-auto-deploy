# Sales API using Java Spring Boot and MongoDB
This is a single Java Spring Boot API microservice with MongoDB backend as a POC. It was started with the Spring Initialzr https://start.spring.io.  It is to show how to do a single API around a particular domain with a database that is its own in a separate programming language and database server. This requires Docker, Java v 8.0 or later, and a web browser to run. Optionally you can use a SQL tool such as mongo-express to view the database if you so desire.

There is a docker-compose YAML file in here to bring up both containers correctly once they are built. And the Jenkinsfile is setup to deploy to OpenShift automatically.

## API Container
* run 'mvn clean package' to compile and package the Java application
* run 'docker build -t salesapi .' to build the containter into a Tomcat image

## Database Container
* run the 'docker build -t salesapidb .' from within the database directory of inventoryapi
* run the database: 'docker run -d --rm --name salesapidb -p 27017:27017 salesapidb'

## Database Setup
Use the Mongo DB database created above and tie it into the application.properties file to match the name, server, and port.

## API Calls

GET http://localhost:xxxx/salesapi/swagger-ui.html for the Swagger UI API documentation.

GET http://localhost:xxxx/salesapi/api/sales gets back a JSON listing of the Inventory class.

GET http://localhost:xxxx/salesapi/api/sales/1 gets back a JSON listing of the Inventory class for the first record. You must add a record to view anything.

POST http://localhost:xxxx/salesapi/api/sales/ will add this Inventory item with the information below
```
{
	"personId" : "71ab7dfc-953f-4821-b221-dcb3cf135068",
    "clientId": 1,
    "inventoryId" : 1,
    "price" : 12.99,
    "discount" : 0.00,
    "tax" : 1.01,
    "quantity": 1,
    "created": "2018-06-10T12:00:00Z"
}
```
PUT http://localhost:xxxx/salesapi/api/sales/5b1d56684083594064783160 will update this Inventory item with the information below
```
{
	"personId" : "71ab7dfc-953f-4821-b221-dcb3cf135068",
    "clientId": 1,
    "inventoryId" : 1,
    "price" : 12.99,
    "discount" : 0.00,
    "tax" : 1.01,
    "quantity": 1,
    "updated": "2018-06-10T16:09:00Z"
}
```
DELETE http://localhost:xxxx/salesapi/api/sales/5b1d56684083594064783160 will delete this Inventory item if found

## ToDo's

* Log events to an event stream
* better documentation
