package Udemy.course.service;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Udemy.course.entity.Student;
import Udemy.course.repository.StudentRepository;
import Udemy.course.request.CreateStudentRequest;

// Writing Logic here
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    // Method to return all the student names from the db
    // We are able to call findAll method, because we are extending JPA repo and
    // that has some methods we can use.....
    public List<Student> getAllStudents() {
        return studentRepository.findAll();

    }

    // Method for POST API

    // Now we have all the data from method payload inside this modal class, We can
    // either use getter and setters or MAKE a contructor inside the Entity class
    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);
        student = studentRepository.save(student);
        return student;

    }

}
