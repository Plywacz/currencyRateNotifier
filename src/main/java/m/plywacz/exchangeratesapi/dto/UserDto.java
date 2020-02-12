package m.plywacz.exchangeratesapi.dto;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.constraints.Name;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {
    @Name(message = "User's last name has to start with Capital letter and rest has to be small")
    @Size(min = 2,max = 100,message = "User's first name must be at least 2 characters long")
    private final String firstName;

    @Name(message = "User's last name has to start with Capital letter and rest has to be small")
    @Size(min = 2,max = 100,message = "User's last name must be at least 2 characters long")
    private final String lastName;

    @NotBlank
    @Email(message = "provide well formatted email")
    private final String email;

    public UserDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
