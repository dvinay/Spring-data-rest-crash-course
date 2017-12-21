## Micro Services using Spring ##
- Small and autonomus services bounded with single context
- Communication between services can be through network call or remote method calls or anything
- Each service expose as API
- We can build and deploy the individual services without effecting other services
- Micro services can be homogenous or heterogenous; you can develop the services on same language or different languages
- Robustness, even though one service is down, other services can run independently - depending on architecure
- Scalable, depending on the load on some services we can increase the cpu/systems for only required services
- Easily deployable
- Reusable and Replaceable
- Uniform Interface and Easy Access

### REST ###
- Representational state transfer (REST) or RESTful web services are a way of providing interoperability between computer systems on the Internet
- REST is a set of principles or architecture guide lines
- HTTP provides uniform interface by providing methods
	- GET - read data from server
	- POST - create data in server
	- PUT - update data resource in server (complete resource update)
	- PATCH - update data resource in server (part of resouce update)
	- DELETE - delete the resource
	- TRACE - to provide the server packet with network path
	- OPTION - provide what http operations does server can perform
- HATEOAS
	- Hypermedia as the Engine of Application state
	- RESTful services should supports the HATEOAS, it should provide the hypermedia links for navigation of other services
	- HAL - Hypertext Application Language, is a popular implementation of HATEOS
- We can implment rest microservices using Spring Data Rest
- Spring Data Rest, simplifies the implementation of REST using spring framework
- Before Spring Data Rest, we use to use Spring MVC
- basic Spring MVC, the layers are Controller Layer-> Services Layer-> DAO/JPA Layer; in this approach if we want to adapt a new change in rest/http calls. we need to implement it as a function in Controller or service layer
- Spring Data has simplified the data accessing
	- create JPA entity
	- extend an interface CrudRepository; which provides data access method
- Spring Data Rest is like a wrapper on spring data with controllers

#### How to create a REST api's using spring data rest ####
- Create a spring starter project in https://start.spring.io/ or STS IDE with JPA, REST Repositories and corresponding database connection dependencies.
- Note: In this project I have used mysql as database
- Create a entity bean with required annotations @Entity and @Id
- Create a repository interface which extends CrudRepository<T, Serializable> ; which helps to generate the database query templates
- Configure the database connection parameters
```JAVA
spring.datasource.name=employee-datasource
spring.datasource.url=jdbc:mysql://localhost:8889/restdb
spring.datasource.username=root
spring.datasource.password=root
```
- Run the project as Spring boot application and request http://localhost:8080/
- [ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/0a55710281fa3f3795b25d114c586e2b86f3e60e)
- Note: Spring Data Rest is not expose id filed, to expose id field create a get method in entity





