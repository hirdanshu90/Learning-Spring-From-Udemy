package Udemy.course.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import Udemy.course.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

// WHY THIS CLASS .........Data goes to the db via this class ..... This is the
// Modal class for GET request API ......

// In summary, using the second method with a response class (StudentResponse)
// provides better control over the data that your API exposes, leading to
// improved security, encapsulation, and flexibility.

// It's a common practice in
// API design to use DTOs (Data Transfer Objects) or response classes to shape
// the data sent to clients.

public class StudentResponse {

    private int id;

    // If we want to ignore this
    // @JsonIgnore
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;

    private String fullName;

    // This takes the Student object as a parameter
    public StudentResponse(Student student) {

        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.fullName = student.getFirstName() + " " + student.getLastName();

    }
}
