package Udemy.course.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// THis is the modal class for our HTTP Post API
public class CreateStudentRequest {

    @JsonProperty("first_name")
    private String firstName;
    private String lastName;
    private String email;

}
