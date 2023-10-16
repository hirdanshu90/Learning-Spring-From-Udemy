package Udemy.course.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Udemy.course.entity.Student;
import Udemy.course.request.CreateStudentRequest;
import Udemy.course.request.UpdateStudentRequest;
import Udemy.course.response.StudentResponse;
// import Udemy.course.response.StudentResponse;
import Udemy.course.service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    // Reading value from the properties file in resources
    // @Value("${app.name}")
    // private String appName;

    // Spring Framework will convert this java object to JSON

    // Calling the method getAllStudents from the service class and mapping this to
    // the getAll endpoint.......,,

    @Autowired
    StudentService studentService;

    @GetMapping("getAll")
    public List<StudentResponse> getAllStudents() {

        // Retrieve the list of Student entities from the service
        List<Student> studentList = studentService.getAllStudents();

        // ...........
        // This code is to convert the list of studentList to studentResponseList
        // ...........

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

    // POST Method ......
    // Spring converts whatever data we are sending to JSON automatically. Now here
    // we need the Json DATA to be converted according to the class, so WE use
    // @RequestBody before the class we want to use and the Payload

    @PostMapping("create")
    // @Valid: For server side validation, and that is mentioned in the modal class,
    // what validation.....
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {

        Student student = studentService.createStudent(createStudentRequest);
        return new StudentResponse(student);

    }

    // PUT Method, basically to update ......
    @PutMapping("update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {

        Student student = studentService.updateStudent(updateStudentRequest);
        // Now converting this student object to student response object. .....
        return new StudentResponse(student);

    }

    // Delete method using @RequestParam method....
    @DeleteMapping("delete")
    public String deleteStudent(@RequestParam int id) {

        return studentService.deleteStudent(id);

    }

    // Delete method using @PathVariable .....
    @DeleteMapping("delete/{id}")
    public String deleteStudent_pathVariable(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }

    // Getting firstname from the database ....
    @GetMapping("getByFirstName/{firstName}")

    public List<StudentResponse> getByFirstName(@PathVariable String firstName) {
        List<Student> studentList = studentService.getByFirstName(firstName);
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

    // Getting Firstname AND lastname together .....
    @GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
    public StudentResponse getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        return new StudentResponse(studentService.getFirstNameAndLastName(firstName, lastName));
         
    }

}
