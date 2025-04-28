Fridge application (backend service)

*** Note: frontend application is in https://github.com/jelenatodorovic/fridge-fe

cd backend
mvn clean install
./mvnw spring-boot:run

Database(H2) -  http://localhost:8080/h2-console/

Fridge app is simple app that provides services for creating different types of fridges.
This app also provides functionality of storing items into fridges, keeping track of 'best before' date 
and removing items.
