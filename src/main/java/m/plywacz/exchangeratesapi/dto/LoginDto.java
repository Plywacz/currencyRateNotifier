package m.plywacz.exchangeratesapi.dto;
/*
Author: BeGieU
Date: 18.02.2020
*/

import com.sun.istack.Nullable;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class LoginDto {
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;

    public LoginDto(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LoginDto loginDto = (LoginDto) o;
        return username.equals(loginDto.username) &&
                password.equals(loginDto.password);
    }

    @Override public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override public String toString() {
        return "LoginDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
