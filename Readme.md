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
- Note: Spring Data Rest is not expose id filed, to expose id field create a get method in entity [ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/f6bef7860e4657cabccecf6e2eba9e1aed3d55f3#diff-acbe2b7d5ad2185133aa02f9d5c6d8a4)
- To customize the application context path set server.context-path properties value
- e.g: set server.context-path=server.context-path properties and hit http://localhost:8080/employee-api/
[ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/2eded407dfb9b182e9eb662fdea96f78ef7bcfb6)

- To enable java.time.* in your project; you have to enable the following dependencies
	- Upgrade hibernate version
		- Springboot uses default hibernate 5.0.1 version package; override the version by using
```XML
<hibernate.version>5.2.10.Final</hibernate.version>
```
	- Add Jackson JSR310 dependency
```XML
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
    <version>2.8.7</version>
</dependency>
```
	- And update the converter
		- JSR310 classes support to serialize and de-serialize the java.time.ZonedTime
		- We have to add @EntityScan annotation with basepackageclasses or basePackage
```JAVA
@SpringBootApplication
@EntityScan(basePackageClasses= {EventmanagementApiApplication.class , Jsr310Converters.class})
public class EventmanagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApiApplication.class, args);
	}
}

```
[res](https://github.com/dvinay/Spring-data-rest-crash-course/tree/master/eventmanagement-api)
- For testing eventmanagement-api
[res](https://github.com/dvinay/Spring-data-rest-crash-course/commit/7cd97e885a38310b1a3bc8c693b489336f180adc#diff-01324a1bb7599f15b7ef76ab44be9581)











