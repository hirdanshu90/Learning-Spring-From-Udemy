package Udemy.course.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// THis is the modal class for our HTTP Post API

// Spring (and in software development in general), a model class is used to
// represent the data in your application. In the context of a REST API, a model
// class represents the structure of the data that is sent and received via the
// API endpoints.

// In summary, using model classes in Spring APIs provides a clear and organized
// way to handle data, enforce validation rules, bind request data, and
// serialize/deserialize JSON. It contributes to the maintainability,
// readability, and reliability of your API code.
public class CreateStudentRequest {

    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;

    private String email;

    // Adding 2 fields in the request modal class wrt to address entity class ......
    private String street;

    private String city;

}
