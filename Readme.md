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

### Paging and Sorting for micro service results ###

- Spring data rest provides a interface called PagingAndSortingRepository which provides the paging and sorting features.
- PagingAndSortingRepository contains the CrudRepository functions; if your repository interface has extended PagingAndSortingRepository no need to extend CrudRepository
- to test paging and sorting; add records more than 20 and hit the get request. It will give self and next last page HATEOS
[ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/b5547066d44e802caca9dc4a81f83dc82228deb0)
- Spring data rest defaultly takes 20 objects as a page size; we can change by setting spring.data.rest.default-page-size property
- Sort the response; we need to pass sort query parameter with data like
http://localhost:8080/eventmanagement-api/events?sort=id,desc
http://localhost:8080/eventmanagement-api/events?sort=id,desc,sort=name
- Spring data rest provides the result in json where key in the class definition order; to format the json key order @JsonPropertyOrder({"name"})
- To get the response based on the request query param requires custom methods
- e.g: /search/findByName?name=data
- add List<Event> findByName(@Param("name") String name); method in EventRepository interface
- request the url http://localhost:8080/eventmanagement-api/events/search/findByName?name=Rest Training2
[ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/b03f49631ab6683ef47a5a22c8629d60198227f9)
- To add paging to findByName; change the return type and include Pagable as a parameter
- Replace List<Event> findByName(@Param("name") String name); as Page<Event> findByName(@Param("name") String name, Pageable pageable);

### How to create custom controller ###
- Spring data rest provides default rest methods; if we want to implement our own business logic or own controller we need create Rest controllers with @RepositoryRestController and @RequestMapping
```JAVA
@RepositoryRestController
@RequestMapping("/events")
public class EventKickOffController {
	@Autowired
	EventRepository eventRepository;
	
	@PostMapping("/start/{id}")
	public ResponseEntity start(@PathVariable("id") Long id) {
		Event event = eventRepository.findOne(id);
		if(event==null) {
			throw new ResourceNotFoundException();
		}
		event.setStarted(true);
		eventRepository.save(event);
		
		return ResponseEntity.ok(event.getName() + " has started");
	}
}
```
- difference between @RepositoryRestController and @RestController is, RepositoryRestController sync with the spring provides rest links and operations; it derives the base path and become part of spring, whereas RestController provides a separate api
- for custom rest api
[ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/b9ce85eebd6143302b40a8ae057d41d414415d33)
- While returning entity as response body in our custom rest controllers; we can convert jpa entity to Rest response by using PersistentEntityResourceAssembler
	- steps to make Jpa entity as response
		- add PersistentEntityResourceAssembler as parameter in controller method
		- while creating ResponseEntity; add assembler parameter toResource method
```JAVA
@PostMapping("/checkin/{id}")
public ResponseEntity<PersistentEntityResource> checkIn(@PathVariable("id") Long id , PersistentEntityResourceAssembler assembler) {
		
	Participant participant = participantRepository.findOne(id);
	if(participant != null) {
		if(participant.getCheckedIn()) {
			throw new ResourceNotFoundException();
		}
		participant.setCheckedIn(true);
		participantRepository.save(participant);
	}
	return ResponseEntity.ok(assembler.toResource(participant));
}
```
[ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/77a019223065eb9dcc9792bc14e70a9357931e60)

### Projections and Excerpts ###
- by default spring Rest represent the view of the entity/domain model
- By using projection, we can represent a view for a particular request based client requirement
	- Partial projection to show part of view
	- Hidden data to hide the part of view
	- Virtual data; combination of set of fields in the part of view
- Excerpts; applying a projection on Resource collections like collection of events or venues.
- To create a Projections for a resource
	- create an interface
	- annotate the interface with @Projection(name="name",types={})
	- and make abstract getter method with field name
Note: while creating projection interfacess; make sure you have to keep it in same package of entities or sub-package of entities
```JAVA
//type indicate the which entity need to check for fields
@Projection(name = "partial", types = { Event.class })
public interface PartialEventProjection {
	public String getName();

	public ZonedDateTime getStartTime();

	public ZonedDateTime getEndTime();
}
```
[ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/e56c36c31d1049e3c89b56e84420dfcf537e360b)
- To test project hit url : http://localhost:8080/eventmanagement-api/events?projection=partial
- It will give output with only name, start time and end time
Note: Projections can expose the @JsonIgnore annotation also; event though a field is @JsonIgnore in entity, If we add the field name in projection.
- To create virtual projections; combination of multiple fields as a rest result. We need to use Spring Expression Language in @Value()
```JAVA
@Projection(name = "virtual", types = { Venue.class })
public interface VirtulaVenueProjection {

	@Value("#{target.streetAddress} #{target.streetAddress2}")
	public String getCombineAddress();

}
```
- To test project hit url : http://localhost:8080/eventmanagement-api/venues?projection=virtual
- It will give output with combined streetAddresses
[ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/c99464c7a46c3fa6ee242edea0692b6c9ddc5dd6)
- Excerpts are used for collection of resources
	- add @RepositoryRestResource annotation to Repository interface
	- pass projection interface as parameter to annotation
```JAVA
@RepositoryRestResource(excerptProjection=PartialEventProjection.class)
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

	Page<Event> findByName(@Param("name") String name, Pageable pageable);

	Page<Event> findByNameAndZoneId(@Param("name") String name, @Param("zoneid") ZoneId zoneId, Pageable pageable);

}
```
- To test project hit url : http://localhost:8080/eventmanagement-api/events
- It will apply the excerpt and display name, starttime and endtime
- It you hit individual element like http://localhost:8080/eventmanagement-api/events/1; it will display the complete resource without projection
- If we want to apply projection to individual element; we need to pass projection parameter like http://localhost:8080/eventmanagement-api/events/1?projection=partial

- Spring provides a HAL browser, to create REST client browser page
- for Spring HAL dependency add spring-data-rest-hal-browser
- after adding dependecy; runthe project and hit the http://localhost:8080/eventmanagement-api ; it will open the HAL browser page and display the HATEOS as documentation for REST APIs
[ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/df8a1af4bb3bed7d155073b512957c3cc18deb79)

### Securing the Micro Services using Spring Security ###
- in Security, two important aspects are Authorization and Authentication.
- Authentication, user login access
- Authorization, whether user is able to perform certain operation
- Steps to secure the API
	- Add the spring-boot-starter-security maven dependency
	- Configure Security Policy : Create a java based security configuration class which extend the WebSecurityConfigurerAdapter from spring; with in this class we will configure all the rules to access api. This step is to secure at URL level
		- configure users and roles using configure method
		- configure the secure links and methods by assining roles to method
	- Secure the method level by using @EnableGlobalMethodSecurity and @PreAuthorize("hasRole('ROLE_ADMIN')"). This step is to secure at method level
- To add the dependency
```XML
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
- To configure the Security configuration; create a class which extend WebSecurityConfigurerAdapter and override configure method
[ref](https://github.com/dvinay/Spring-data-rest-crash-course/commit/8f64650ff41d2c734d9a714882dd56aca12c470b)
- To create users and roles; we need to provide auth manager with user and roles
in configure method
```JAVA
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin").password("admin").roles("ADMIN");
}
```




