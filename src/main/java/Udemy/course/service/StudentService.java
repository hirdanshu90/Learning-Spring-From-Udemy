package Udemy.course.service;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Udemy.course.entity.Student;
import Udemy.course.repository.StudentRepository;

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

}
