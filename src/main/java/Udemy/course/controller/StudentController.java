package Udemy.course.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Udemy.course.entity.Student;
import Udemy.course.response.StudentResponse;
// import Udemy.course.response.StudentResponse;
import Udemy.course.service.StudentService;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    // Reading value from the properties file in resources
    // @Value("${app.name}")
    // private String appName;

    // Spring Framework will convert this java object to JSON

    // Calling the method getAllStudents from the service class and mapping this to
    // the getAll endpoint.......
    @Autowired
    StudentService studentService;

    // We should not do use the first method, instead use the second one ......

    // FIRST MEthod (That is commented and not used...)

    // This method directly returns a list of Student objects retrieved from the
    // database. The response will contain the raw student data. If this method is
    // used, the client consuming the API would receive the actual database
    // entities, including all fields defined in the Student class, which might
    // include sensitive information. In a real-world scenario, exposing raw entity
    // objects to clients is generally discouraged due to security and encapsulation
    // concerns. It's often better to create a separate response model that exposes
    // only the necessary fields and hides sensitive data.
    @GetMapping("/getAll")
    // public List<Student> getAllStudents()
    // {
    // return studentService.getAllStudents();
    // }

    // SECOND method ....
    // The StudentResponse class contains only specific fields (id, first name, last
    // name, and email) that are meant to be exposed to clients. This approach
    // provides several benefits:
    public List<StudentResponse> getAllStudents() {

        // Retrieve the list of Student entities from the service
        List<Student> studentList = studentService.getAllStudents();

        // // Create a new ArrayList to store StudentResponse objects
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        // Iterate through the list of Student entities and convert them to
        // StudentResponse objects
        studentList.stream().forEach(student -> {

            // // For each Student entity, create a new StudentResponse object and add it to
            // the response list
            studentResponseList.add(new StudentResponse(student));

        });
        // Return the list of StudentResponse objects
        return studentResponseList;
    }

}
