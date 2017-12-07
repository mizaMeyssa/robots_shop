# Robots REST API

This is a Spring based REST API for Robots Shop 

## Authors

* **Maissa Jabri**  [mizaMeyssa](https://github.com/mizaMeyssa)

## Prerequisites

* PostgreSQL (version 9.6.4): DBMS
* Apache Maven (version 3.5.2) Build and dependecies management tool
* Eclipse luna (Release 2 (version 4.4.2)) EDI
* JDK (version 1.8.0_131) 

## Installation: Developer Mode

* Install all the prerequisites 
* Download the package and go under robots_shop_backend folder
* Create The database as follows 
Go under robots_shop_backend/db folder and run 
```
sh install_db.sh
```
You shall be able to connect to your database using these properties 
```
PGUSER='postgres'
PGHOST='localhost'
PGPASSWORD='root'
PGDATABASE='robots'
PGPORT='5432'
```
* Run the application
Go back under robots_shop_backend/ and run
```
mvn spring-boot:run
```
Now your REST API shall be up and running and accessible via curl commands or any REST client (Postman, ARC of chrome, etc.)
* Run your [initial batch](https://github.com/mizaMeyssa/initialBatch) as follows
Go under robots_shop_backend/db/db_init folder and to download node dependencies run
```
npm install
```
run the initial batch as follows
```
sh initial-batch.sh
```
Now your database has been fed with your init data
To Launch the web application go to this repo: [robots_shop_frontend](https://github.com/mizaMeyssa/robots_shop_frontend)
## Installation: Integrator Mode
