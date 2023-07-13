# Java-Trademarks-Api

## Task
1) Create a script for setting up the database and populating it just with the state of trademarks.
2) Setup the server and implement RESTsuitable for trademark-searching service. The service is meant to check if a trademark already exists and if it does, return the basic properties of it.
For the sake of the assignment, let's focus just on word trademarks (not visual/audible/etc), you can distinguish them by the "MarkFeature" attribute being "Word". Here's an example of a record in the data dump: https://pastebin.com/8G7ysw2X
3) The server should support at least:
- searching in a database for exact trademark;
- fuzzily finding some 'nearest' trademarks (for some meaningful definition of 'nearest').

## Database
Start up the postgres DB with docker-compose
```
docker-compose up
```
DB connection data
```
url=jdbc:postgresql://localhost:5432/postgres
username=postgres
password=pass123
```
To run migrations use Liquibase:
```
mvn liquibase:update
```

## Swagger
The API provides user with Swagger UI page, it is available after successful program start on the next url
```
http://localhost:8080/swagger-ui/index.html
```
All searchings bound with the field MarkVerbalElementText
