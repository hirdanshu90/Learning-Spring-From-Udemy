package Udemy.course.repository;

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

}
