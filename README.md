# ![Marcura logo](src/main/resources/images/marcura_logo.jpeg?raw=true) Marcura Exchange Rate API

### Overview:
Internal Marcura API for exchange rates to consolidate throughout all of its applications how exchange rates are calculated for 2 given currencies.\
Exchange rate data will be collected and persisted via a ExchangeRateScheduler running once a day automatically at 12:05 AM GMT.\
Manual trigger retrieval of latest exchange rates is also possible via the additional API (PUT /exchange).

### Dependencies: 
Java 11, Docker, docker-compose, Maven, Postman

Recommended IDE: Jetbrains IntelliJ Idea

### Installation:
Docker and docker-compose installation is required for postgres database to be created.
Inside the root of the project, enter the follow command inside terminal, docker-compose up 
This command will trigger the docker-compose.yml configuration to create a postgres database.

### How to use:
 API (PUT /exchange) (Retrieve And Persists Latest Exchange Rates):
  ![Alt text](src/main/resources/images/api_put_example.png?raw=true)

 API (GET /exchange) (Calculate Exchange Rate):
  - Response with valid from and to currency with valid date
   ![Alt text](src/main/resources/images/api_get_with_date_example.png?raw=true)
  - Response with valid from and to currency with no date
   ![Alt text](src/main/resources/images/api_get_no_date_example.png?raw=true)
  - Response with valid from and to currency with valid date old
   ![Alt text](src/main/resources/images/api_get_with_old_date_example.png?raw=true)
  - Response with invalid date format
   ![Alt text](src/main/resources/images/api_get_invalid_date_format_example.png?raw=true)
  - Response with invalid currency format
   ![Alt text](src/main/resources/images/api_get_invalid_currency_format_example.png?raw=true)
  - Response with no results found
   ![Alt text](src/main/resources/images/api_get_currency_not_found_example.png?raw=true)
    
### API reference:
Swagger documentation on how to use API's - http://localhost:8080/swagger-ui.html
![Alt text](src/main/resources/images/swagger.png?raw=true)

#### Exchange Data Model 
An assumption was made that no historical exchange rates will be persisted, i.e. the database will only store the latest exchange rate between the base currency and the secondary currency. For this reason a composite primary key was used. The use of a composite primary key utilises the database engine to ensure that not more than one exchange rate per currency pair per day can be stored. If histric exchange rates need to be stored, the API and database/object model can be amended to store a version as part of the primary key. Clients would also then be notified in the response object as to the version of the exchange rate that was used.
![Alt text](src/main/resources/images/exchange_entity.png?raw=true)

#### Exchange Usage Data Model
Exchange usage is also captured and stored. This can be used in future for further data analytics.
![Alt text](src/main/resources/images/exchange_usage_entity.png?raw=true)