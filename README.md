[![License MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/bfwg/angular-spring-jwt-starter/blob/master/LICENSE)


# Angular Spring Boot Favorite Movies


## Quick start
**Make sure you have Maven and Java 11 or greater**
```bash
# run from project root
mvn clean package

# change directory to the repo's backend build folder
cd server/target

# start the app
# both backend and frontend are packaged into a single jar
java -jar favorite-movies-0.0.1.jar
```
### open frontend from browser
http://localhost:8080

### Database
This project is using H2 database from a file in server/target

To clear database, simply delete the spring-boot-h2-db.mv.db file
and restart the app

To access database:
* http://localhost:8080/h2
* JDBC URL: jdbc:h2:file:./spring-boot-h2-db
* Leave the rest as-is and connect

## License
[MIT](/LICENSE)

