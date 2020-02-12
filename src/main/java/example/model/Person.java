package example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String ip_address;
    private int age;
}
