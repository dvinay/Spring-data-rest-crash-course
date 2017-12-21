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







