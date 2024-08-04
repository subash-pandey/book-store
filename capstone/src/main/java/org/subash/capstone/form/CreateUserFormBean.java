package org.subash.capstone.form;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserFormBean {

    private Integer userId;

    @NotEmpty(message = "First Name is required")
    private String firstName;
    private String lastName;

    @NotEmpty(message = "Email is required")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).*$", message = "Password must contain at least one number and one uppercase letter")
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
