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
@Table(name = "address")
@NoArgsConstructor
@ToString

// Made a separate table in the db, and
public class Address {

    // Primary Key
    @Id
    // for PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

}
