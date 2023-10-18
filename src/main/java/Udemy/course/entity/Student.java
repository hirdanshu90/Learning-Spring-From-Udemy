package Udemy.course.entity;

import Udemy.course.request.CreateStudentRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

// REferencing to entity and the table name .....
@Entity
@Table(name = "student")
@NoArgsConstructor
@ToString

// Class representing database in the mySQL db

// DATABASE ENTITY CLASSSSSSSSSSSS
public class Student {

    // Primary Key
    @Id
    // for PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // This field is not there in the database, but we need this so we make this by
    // @Transient .........
    @Transient
    private String fullName;

    // Contructor for POST API, this will be used inside the service class, passing
    // CreateStudentRequest as parameters.
    public Student(CreateStudentRequest createStudentRequest) {
        this.firstName = createStudentRequest.getFirstName();
        this.lastName = createStudentRequest.getLastName();
        this.email = createStudentRequest.getEmail();
        this.fullName = createStudentRequest.getFirstName() + " " + createStudentRequest.getLastName();
    }

}
