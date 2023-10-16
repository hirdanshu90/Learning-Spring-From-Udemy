package Udemy.course.service;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Udemy.course.entity.Student;
import Udemy.course.repository.StudentRepository;
import Udemy.course.request.CreateStudentRequest;
import Udemy.course.request.UpdateStudentRequest;

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
        // Updating the repo.....
        // Save method is for both to CREATE and INSERT the record
        student = studentRepository.save(student);
        return student;

    }

    // Method for PUT API, (To update some record)
    // Whatever coming from controller we will pass here updateStudentRequest
    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        // Finding the id by ID from the student repo ....
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();

        // Checking for first name ......
        if (updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isEmpty()) {
            // If its coming then set the first name to the student entity class.
            student.setFirstName(updateStudentRequest.getFirstName());
        }
        // Updating the repo.....
        // Save method is for both to CREATE and INSERT the record
        student = studentRepository.save(student);
        return student;

    }

    // Delete API LOGIC via @RequestParam method ......

    public String deleteStudent(int id) {

        // Method provided by JPA using that .....
        studentRepository.deleteById(id);

        return "student has been deleted";
    }
}
