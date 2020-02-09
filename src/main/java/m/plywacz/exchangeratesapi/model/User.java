package m.plywacz.exchangeratesapi.model;
/*
Author: BeGieU
Date: 08.02.2020
*/

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User extends BasicEntity {
    private String firstName;
    private String lastName;

    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private final Set<Notification> notifications = new HashSet<>();

    public User() {
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

    //todo insert toString and Equals hashcode, when POJO is finished
}
