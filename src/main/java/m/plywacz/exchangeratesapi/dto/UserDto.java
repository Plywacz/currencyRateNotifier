package m.plywacz.exchangeratesapi.dto;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.constraints.Name;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserDto {

    @Size(min = 2,max = 100,message = "User's first name must be at least 2 characters long")
    @Name(message = "User's last name has to start with Capital letter and rest has to be small")
    private final String firstName;

    @Size(min = 2,max = 100,message = "User's last name must be at least 2 characters long")
    @Name(message = "User's last name has to start with Capital letter and rest has to be small")
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

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(email, userDto.email);
    }

    @Override public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    @Override public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
