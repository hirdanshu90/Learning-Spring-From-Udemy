package Udemy.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Udemy.course.entity.Student;

@Repository
// In the JPA repository, we pass the name if the ENTITY class (Student class)
// and the datatype of the Primary Key (Here id)
// JPA repo is a combination of 2 repositories. (Both are interfaces)

// By extending JpaRepository, your StudentRepository inherits a wealth of
// methods for working with Student entities. These methods include basic
// database operations such as save, findById, findAll, deleteById, etc. You
// don't need to implement these methods; they are provided by Spring Data JPA
// based on the entity type (Student) and the primary key type (Integer).

// The repository methods handle transactions and database connections
// transparently.
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // creating method for the API to find the first name, returning a list of that
    // particular firstnamess can be more than 1

    // JPA will create the native SQL query and will hit the response ...
    List<Student> findByFirstName(String firstName);

    Student findByFirstNameAndLastName(String firstName, String LastName);

}
