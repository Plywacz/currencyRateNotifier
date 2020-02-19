package m.plywacz.exchangeratesapi.dto;
/*
Author: BeGieU
Date: 09.02.2020
*/

import com.sun.istack.Nullable;
import m.plywacz.exchangeratesapi.constraints.Name;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserDto {

    @Size(min = 2, max = 100, message = "User's first name must be at least 2 characters long")
    @Name(message = "User's last name has to start with Capital letter and rest has to be small")
    private final String firstName;

    @Size(min = 2, max = 100, message = "User's last name must be at least 2 characters long")
    @Name(message = "User's last name has to start with Capital letter and rest has to be small")
    private final String lastName;

    @NotBlank
    @Email(message = "provide well formatted email")
    private final String email;

    @Size(min = 6, max = 30, message = "User's username must be at least 6 characters long")
    private final String username;

    @Size(min = 6, max = 30, message = "User's password must be at least 6 characters long")
    private final String password;

    @Nullable
    private final String adminKey;

    public UserDto(String firstName, String lastName, String email,
                   String username, String password, String adminKey) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.adminKey = adminKey;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAdminKey() {
        return adminKey;
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
