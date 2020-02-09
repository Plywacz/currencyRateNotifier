package m.plywacz.exchangeratesapi.model;
/*
Author: BeGieU
Date: 08.02.2020
*/

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Notification extends BasicEntity {
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private final Set<Reading> readings = new TreeSet<>();

    private Currency currency;
    private long frequency;

    public Notification() {
    }

    //todo insert toString and Equals hashcode, when POJO is finished


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Reading> getReadings() {
        return readings;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }
}
