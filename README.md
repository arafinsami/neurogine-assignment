1. checkout the projects and build three Spring Boot projets are eureka(Discovery server) , neurogine-api and neurogine-gateway(Our Gateway) using 
   gradle clean build
   
   NB: You have to build project separately.
   
2. Now run the front-end project(neurogine-portal) as " ng serve " and the project URL is : http://localhost:4200/

3. Discovery server URL is : http://localhost:8761/, here to login to discovery server, username and password is : a

4. Our API gateway URl : http://localhost:9999/actuator/health

5. Call Store Server app through API Gateway as : http://localhost:9999/store-app-service/swagger-ui/index.html

6. MongoDB properties are: 

        spring.data.mongodb.host=localhost
	spring.data.mongodb.port=27017
	spring.data.mongodb.database=STORES_DB
	spring.data.mongodb.username=arafin
	spring.data.mongodb.password=arafin
	spring.data.mongodb.auto-index-creation=true
	
	In my case, I created Database as STORES_DB and
	Username and password is : arafin
	
5. Required environment: 

  1. Open JDK: 17
  2. MongoDB
  3. NodeJS (18)
  4. Angular 14
  5. Gradle version : 8.5
  6. IDE: Intellij IDEA
  

