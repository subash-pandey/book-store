package org.subash.capstone.form;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserFormBean {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phone;
    private  String role;


}
