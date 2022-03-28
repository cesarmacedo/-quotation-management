# -quotation-management

steps to generate quote-management-1.0-SNAPSHOT.jar with Maven:

1. install Maven 
2. In the project root directory Run `mvn clean package` command

Steps to generate dockerFile :

1. install docker 
2. In the project root directory Run `docker image built -t quotation-management .` command
3. IChecking the generated image Run `docker image ls` command

Accessing Swagger:

After going up image Access the browser and enter the address http://localhost:8081/swagger-ui.html

