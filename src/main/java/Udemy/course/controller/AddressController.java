package Udemy.course.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Udemy.course.entity.Student;
import Udemy.course.request.InQueryRequest;
import Udemy.course.response.StudentResponse;
import Udemy.course.service.StudentService;

@RestController
@RequestMapping("/api/address/")
public class AddressController {

    // Logger Levels: Error < Warn < Info < Debug < Trace, ....... .. By default,
    // Info level is
    // there, that means below levels are automatically active.

    // Importing the Logger Class from slf4j.Logger
    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    StudentService studentService;

    @GetMapping("getEverything")
    public List<StudentResponse> getAllStudents() {

        // Putting Log levels ... When we will hit this API endpoint, we will get these
        // in the logss
        logger.debug("Inside debug");
        logger.info("Inside Info");

        // Retrieve the list of Student entities from the service
        List<Student> studentList = studentService.getAllStudents();

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {

            studentResponseList.add(new StudentResponse(student));

        });
        // Return the list of StudentResponse objects
        return studentResponseList;
    }

    // Basically a get method where We provide a list of firstnames, and from that,
    // if something matches we get that in the form of a list.
    @GetMapping("getByFirstNameInAddress")
    public List<StudentResponse> getByFirstName(@RequestBody InQueryRequest inQuesryRequest) {

        // Logger ............
        logger.info("inQueryRequest = " + inQuesryRequest);

        List<Student> studentList = studentService.getFirstNameIn(inQuesryRequest);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {

            studentResponseList.add(new StudentResponse(student));

        });

        // Logging the list .......... We need toString for the info to be in the logsss. 
        logger.info("StudentResponseList = " + studentResponseList);

        return studentResponseList;

    }

}
