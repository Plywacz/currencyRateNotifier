package m.plywacz.exchangeratesapi.model;
/*
Author: BeGieU
Date: 08.02.2020
*/

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User extends BasicEntity {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final Set<Notification> notifications = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Credentials credentials;

    public User() {
    }

    public User addNotification(Notification notification) {
        notifications.add(notification);
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @JsonIgnore
    public String getUsername() {
        return credentials.getUsername();
    }

    @JsonIgnore
    public String getPassword() {
        return credentials.getPassword();
    }

    @JsonIgnore
    public String getRole() {
        return credentials.getRole();
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User otherUser = (User) o;
        return Objects.equals(this.getId(), otherUser.getId());
    }

    @Override public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", notifications=" + notifications +
                "} " + super.toString();
    }
}
