SPRING BOOT:

@SpringBootApplication
public class Application {
public static void main(String[] args) {
SpringApplication.run(Application.class, args);
}
@Bean
public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
return args -> {
System.out.println("Let's inspect the beans provided by Spring Boot:");
String[] beanNames = ctx.getBeanDefinitionNames();
Arrays.sort(beanNames);
for (String beanName : beanNames) {
System.out.println(beanName);
}
};
}
}
@SpringBootApplication annotation that adds:
@Configuration: Tags the class as a source of bean definitions for the application context.
@EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.
@ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.
The main() method uses Spring Boot’s SpringApplication.run() method to launch an application.There is no web.xml file. This web application is 100% pure Java and you did not have to deal with configuring any plumbing or infrastructure.
There is also a CommandLineRunner method marked as a @Bean, and this runs on start up. It retrieves all the beans that were created by your application or that were automatically added by Spring Boot.

@SpringBootApplication
We use this annotation to mark the main class of a Spring Boot application.
@SpringBootApplication encapsulates @Configuration, @EnableAutoConfiguration, and @ComponentScan annotations with their default attributes.
@SpringBootApplication
class VehicleFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleFactoryApplication.class, args);
    }

}

@RestController

Create a Simple Web Application
Now you can create a web controller for a simple web application
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
@RequestMapping("/")
public String index() {
return "Greetings from Spring Boot!";
}
}
The class is marked as a @RestController, meaning it is ready for use by Spring MVC to handle web requests. @RequestMapping maps ‘/’ to the index() method. When invoked from a browser or by using curl on the command line, the method returns pure text. That is because @RestController combines @Controller and @ResponseBody, two annotations that results in web requests returning data rather than a view.

@JsonIgnore

If we don’t want to populate a field in JSON, mention @JsonIgnore above that field, and that will be ignored.

In summary, in a Spring Boot application:

JPA is the specification that defines the standard interface for ORM in Java applications.

JPQL is a query language used with JPA to perform database operations in an object-oriented way.

Hibernate is a popular ORM framework and a JPA implementation that simplifies the database interaction in Java applications.

ORM is a programming technique that Hibernate and other frameworks use to bridge the gap between object-oriented programming and relational databases.

@Entity
@Table(name = “”)

ENTITY class.

Entity is the class that will represent the fields in the database.
Every table will have its own Entity class, Every entity class will have their own Repository class, and TOGETHER CRUD operations will be performed.

@Service

A. @Service Annotation
In an application, the business logic resides within the service layer so we use the @Service Annotation to indicate that a class belongs to that layer. It is a specialization of @Component Annotation. One most important thing about the @Service Annotation is it can be applied only to classes. It is used to mark the class as a service provider. So overall @Service annotation is used with classes that provide some business functionalities. Spring context will autodetect these classes when annotation-based configuration and classpath scanning is used.

B. @Repository Annotation
@Repository Annotation is also a specialization of @Component annotation which is used to indicate that the class provides the mechanism for storage, retrieval, update, delete and search operation on objects. Though it is a specialization of @Component annotation, so Spring Repository classes are autodetected by the spring framework through classpath scanning. This annotation is a general-purpose stereotype annotation which is very close to the DAO pattern where DAO classes are responsible for providing CRUD operations on database tables.
Each Repository is an INTERFACE…..

C. @Controller Annotation
Spring @Controller annotation is also a specialization of @Component annotation. The @Controller annotation indicates that a particular class serves the role of a controller. Spring Controller annotation is typically used in combination with annotated handler methods based on the @RequestMapping annotation. It can be applied to classes only. It’s used to mark a class as a web request handler. It’s mostly used with Spring MVC applications. This annotation acts as a stereotype for the annotated class, indicating its role. The dispatcher scans such annotated classes for mapped methods and detects @RequestMapping annotations.

@Service Annotation
@Repository Annotation
@Controller Annotation
@Service annotation is used with classes that provide some business functionalities.
@Repository Annotation is used to indicate that the class provides the mechanism for storage, retrieval, update, delete and search operation on objects.
@Controller annotation indicates that a particular class serves the role of a controller.
@Service Annotation is a specialization of @Component Annotation.
@Repository Annotation is also a specialization of @Component Annotation.
@Controller annotation is also a specialization of @Component annotation.
It is used to mark the class as a service provider.
It is used to mark the interface as DAO (Data Access Object) provider.
It’s used to mark a class as a web request handler.
It is a stereotype for the service layer.
It is also a stereotype for the DAO layer.
It is also a stereotype for the presentation layer (spring-MVC).
Switch can be possible. But it is not recommended.
Switch can be possible. But it is not recommended.
We cannot switch this annotation with any other like @Service or @Repository.
It is a Stereotype Annotation.
It is also a Stereotype Annotation.
It is also a Stereotype Annotation.

**\***
@AUTOWIRED Annotation.
**\***

If we need instance of anything, we use @Autowired

- IMP EXPLANATION **\***

NOW inside the Controller class, where we are extracting the data from the service package:

In the provided code, there are two methods for the `getAllStudents` endpoint in the `StudentController` class. Let's discuss the differences and benefits of using the second method, which returns a `List<StudentResponse>` instead of a `List<Student>`.

Calling the “getAll” API endpoint ………

**First Method:**

```java
public List<Student> getAllStudents() {
	return studentService.getAllStudents();
}
```

This method directly returns a list of `Student` objects retrieved from the database. The response will contain the raw student data. If this method is used, the client consuming the API would receive the actual database entities, including all fields defined in the `Student` class, which might include sensitive information. In a real-world scenario, exposing raw entity objects to clients is generally discouraged due to security and encapsulation concerns. It's often better to create a separate response model that exposes only the necessary fields and hides sensitive data.

**Second Method:**

```java
public List<StudentResponse> getAllStudents() {
	List<Student> studentList = studentService.getAllStudents();
	List<StudentResponse> studentResponseList = new ArrayList<>();

	studentList.forEach(student -> {
    	studentResponseList.add(new StudentResponse(student));
	});
	return studentResponseList;
}
```

In the second method, the controller retrieves the list of `Student` objects from the service, then converts each `Student` object into a `StudentResponse` object. The `StudentResponse` class contains only specific fields (id, first name, last name, and email) that are meant to be exposed to clients. This approach provides several benefits:

1. **Encapsulation:** The client only sees the fields that you expose in the `StudentResponse` class. Fields that are not meant to be exposed (e.g., sensitive information) can be excluded.

2. **Flexibility:** The response structure can be tailored to the specific needs of the client. If the client only needs certain fields, you can customize the `StudentResponse` class accordingly without modifying the database entity.

3. **Security:** By controlling what data is exposed, you can prevent sensitive information from being unintentionally leaked to clients.

4. **Versioning:** If the internal structure of the `Student` entity changes, it won't affect the API contract as long as the fields in `StudentResponse` remain the same. This allows for easier versioning of APIs.

In summary, using the second method with a response class (`StudentResponse`) provides better control over the data that your API exposes, leading to improved security, encapsulation, and flexibility. It's a common practice in API design to use DTOs (Data Transfer Objects) or response classes to shape the data sent to clients.

EXPLAINING COde for the 2nd method:

Certainly! Let's break down the code for the `getAllStudents()` method in the `StudentController` class:

```java
public List<StudentResponse> getAllStudents() {
	// Retrieve the list of Student entities from the service
	List<Student> studentList = studentService.getAllStudents();

	// Create a new ArrayList to store StudentResponse objects
	List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

	// Iterate through the list of Student entities and convert them to StudentResponse objects
	studentList.forEach(student -> {
    	// For each Student entity, create a new StudentResponse object and add it to the response list
    	studentResponseList.add(new StudentResponse(student));
	});

	// Return the list of StudentResponse objects
	return studentResponseList;
}
```

''''''''''''''''''''''''''''
Explanation:
''''''''''''''''''''''''''''

1. **`List<Student> studentList = studentService.getAllStudents();`**:

   - Calls the `getAllStudents()` method from the `studentService` to retrieve a list of `Student` entities from the database.

2. **`List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();`**:

   - Initializes a new ArrayList to store `StudentResponse` objects. This list will hold the converted responses.

3. **`studentList.forEach(student -> { ... });`**:

   - Uses the `forEach` method to iterate through each `Student` object in the `studentList`.

4. **`studentResponseList.add(new StudentResponse(student));`**:

   - For each `Student` object, creates a new `StudentResponse` object using the constructor of the `StudentResponse` class. The constructor takes a `Student` object as a parameter and initializes the `StudentResponse` fields with values from the corresponding `Student` object.
   - Adds the newly created `StudentResponse` object to the `studentResponseList`.

5. **`return studentResponseList;`**:
   - Returns the list of `StudentResponse` objects. This list contains the converted response data that will be sent to the client.

In summary, this code takes the raw `Student` entities retrieved from the database, converts them into `StudentResponse` objects (which likely contain a subset of fields and a specific structure for the response), and returns a list of these `StudentResponse` objects. This approach ensures that only the necessary and safe data is exposed to clients, providing better control over the API response and improving security and encapsulation.

''''''''''''''''''''''''''''
API INFORMATION:
'''''''v'''''''''''''''''''''
''''''''''''''''''''''''''''
''''''''''''''

- FIRST: Request Payload.
  '''''''''''''''''''''
  In the context of API calls, the **request payload** refers to the data sent by the client to the server as part of an HTTP request. It is used to send data to the server, typically when creating or updating a resource. The request payload can be in various formats such as JSON, XML, or form data.

For example, in a POST request to create a new user, the client might send a request payload in JSON format like this:

```json
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "age": 30
}
```

In this case, the JSON object containing `name`, `email`, and `age` properties is the request payload. The server processes this data to create a new user record in the database.

Similarly, in a PUT or PATCH request to update an existing user, the client might send a request payload containing the updated information for the user.

Request payloads are essential for transmitting data between clients and servers, allowing applications to interact and exchange information over the web. The format of the request payload is typically defined by the API's specifications and the agreement between the client and server.

''''''''''''''''''''''''''''

- SECOND: @RequestBody
  ''''''''''''''''''''''''''''

For getting JSON into our modal class object, here: CreateStudentRequest.java class.

In Spring Framework, the @RequestBody annotation is used in a Spring MVC controller method to bind the HTTP request body to a parameter in the method. It indicates that a method parameter should be bound to the body of the HTTP request, and Spring will automatically convert the request body to the appropriate Java object.

Using @RequestBody simplifies the process of handling incoming JSON or XML payloads in Spring applications, allowing you to work with Java objects directly instead of dealing with raw JSON strings.

''''''''''''''''''''''''''''

- THIRD: save() method from JPA
  ''''''''''''''''''''''''''''

  public Student createStudent(CreateStudentRequest createStudentRequest)
  {
  Student student = new Student(createStudentRequest);
  student = studentRepository.save(student);
  return student;
  }

  What is this save method doing ?

The save method in Spring Data JPA repositories is used to persist an entity object (in this case, a Student object) to the database. When you invoke studentRepository.save(student), Spring Data JPA will perform the necessary operations to either insert a new record or update an existing record in the database based on the state of the student object.

Here's how it works:

If the student object has a null or unset primary key (id in your case):

Spring Data JPA recognizes that the object is a new entity that hasn't been persisted to the database yet.
It generates an INSERT SQL statement and inserts a new row in the corresponding table with the values from the student object's fields.

The createStudent method receives a CreateStudentRequest object, creates a new Student object based on this request, and then saves it using the save method.

The save method handles the database interaction, allowing you to focus on creating and manipulating Java objects, leaving the database operations to Spring Data JPA.

''''''''''''''''''''''''''''
WHY Modal class?
''''''''''''''''''''''''''''
In summary, using model classes in Spring APIs provides a clear and organized way to handle data, enforce validation rules, bind request data, and serialize/deserialize JSON. It contributes to the maintainability, readability, and reliability of your API code.

''''''''''''''''''''''''''''
''''''''''''''''''''''''''''''''''''''''''
WHY REPOSITORY IS REQUIRED ??
''''''''''''''''''''''''''''''''''''''''''
''''''''''''''''''''''''''''

In Spring Data JPA, the **repository interface** plays a crucial role in simplifying the data access layer of your application. Specifically, in your provided code:

```java
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
```

Here's what the repository class is doing and why it's important:

1. **Data Access Abstraction:**

   - The repository interface abstracts away the details of how data is fetched, stored, and manipulated in the underlying database. Developers don't need to write boilerplate code for basic CRUD (Create, Read, Update, Delete) operations.

2. **Inheritance from `JpaRepository`:**

   - By extending `JpaRepository`, your `StudentRepository` inherits a wealth of methods for working with `Student` entities. These methods include basic database operations such as `save`, `findById`, `findAll`, `deleteById`, etc. You don't need to implement these methods; they are provided by Spring Data JPA based on the entity type (`Student`) and the primary key type (`Integer`).

3. **Custom Query Methods:**

   - You can define custom query methods in the repository interface by following the naming conventions. For example, if you want to find a `Student` by their `firstName`, you can define a method like this: `Student findByFirstName(String firstName)`. Spring Data JPA will automatically generate the query based on the method name.

4. **Type Safety:**

   - Repository interfaces provide type safety. When you create a method in the repository, the compiler checks if the method name and parameters match the entity's properties and types. This helps catch errors at compile time rather than runtime.

5. **Integration with Spring and JPA:**

   - Repositories are automatically detected by Spring and are instantiated as Spring beans. This means they can be injected into other Spring components (such as services) and used throughout your application. The repository methods handle transactions and database connections transparently.

6. **Simplified Testing:**
   - When writing tests, you can easily create mock implementations or use Spring's testing support to test your components without worrying about the actual database interactions. This makes unit testing much simpler.

In summary, the repository class abstracts away the complexities of data access and provides a clean, high-level interface for interacting with the underlying database. It enhances code readability, maintainability, and testability while reducing the amount of boilerplate code that developers need to write.
