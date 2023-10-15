package Udemy.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Udemy.course.entity.Student;

@Repository
// In the JPA repository, we pass the name if the ENTITY class (Student class)
// and the datatype of the Primary Key (Here id)
// JPA repo is a combination of 2 repositories. (Both are interfaces)
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
