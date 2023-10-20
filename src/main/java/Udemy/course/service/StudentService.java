package Udemy.course.service;

import java.util.List;

import org.aspectj.weaver.ast.And;
// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import Udemy.course.entity.Address;
import Udemy.course.entity.Student;
import Udemy.course.repository.AddressRepository;
import Udemy.course.repository.StudentRepository;
import Udemy.course.request.CreateStudentRequest;
import Udemy.course.request.InQueryRequest;
import Udemy.course.request.UpdateStudentRequest;
import Udemy.course.response.StudentResponse;

// Writing Logic here
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

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

        // Upating the address table as well when creating the student table .........
        // because address table has the relationship woth the student table....

        // So the order is first, insert the record into the parent table, obtain the
        // foreign key, and then
        // insert the record into the child table.

        // First, we need to create the object
        // of address entity class and from here we are getting the address
        // Street City, right?
        // And then using the address repository, we will persist that object into the
        // address table.

        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        // We need to call and pass the entity class object and in return we will get
        // that created record, basically
        // object of entity class.
        address = addressRepository.save(address);

        student.setAddress(address);

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

    // Method for getting firstName from the db ....
    public List<Student> getByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);

    }

    // Method for getting firstName and lastName from the db ......
    public Student getFirstNameAndLastName(String firstName, String lastName) {
        Student fn = studentRepository.findByFirstNameAndLastName(firstName, lastName);
        return fn;

    }

    // Method for getting firstName in .... from the db
    public List<Student> getFirstNameIn(InQueryRequest inQueryRequest) {
        return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
    }

    // Method for getting pagination and getting a list of students ......

    // public static List<Student> getAllStudentsWithPagination(int pageNo, int
    // pageSize) {
    // // -1 beacause it is a 0 based index static method ....
    // Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    // return studentRepository.findAll(pageable);
    // }

    // Method to sort the data in db .....

    // public List<Student> getAllStudentWithSorting () {
    // Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
    // return studentRepository.findAll(sort);
    // }

    // Method for Like in SQL
    public List<Student> like(String firstName) {
        return studentRepository.findByFirstNameContains(firstName);

    }

    // Method for StartsWith in SQL....
    public List<Student> startsWith(String firstName) {
        return studentRepository.findByFirstNameStartsWith(firstName);

    }

    // Method to update firstName using id
    public Integer updateStudentWithJpql(int id, String firstName) {
        return studentRepository.updateFirstName(id, firstName);

    }
}
