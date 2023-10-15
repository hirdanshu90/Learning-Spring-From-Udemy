package Udemy.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// REferencing to entity and the table name .....
@Entity
@Table(name = "student")

// Class representing database in the mySQL db

// DATABASE ENTITY CLASSSSSSSSSSSS
public class Student {

// Primary Key 
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;

}
