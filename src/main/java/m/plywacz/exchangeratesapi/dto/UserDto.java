package m.plywacz.exchangeratesapi.dto;
/*
Author: BeGieU
Date: 09.02.2020
*/

import javax.validation.constraints.NotBlank;

public class UserDto {
    private final String firstName;

    private final String lastName;

    private final String email;

    public UserDto(@NotBlank String firstName,
                   @NotBlank String lastName,
                   @NotBlank String email) {
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
