package Udemy.course.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// This modal class is for PUT API, and we can mention what attributes to update......
public class UpdateStudentRequest {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

}
