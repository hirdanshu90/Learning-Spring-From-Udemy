package Udemy.course.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
// THis is the modal class for getting the firstNames from the db ....
public class InQueryRequest {

    private List<String> firstNames;
    
}
