package m.plywacz.exchangeratesapi.model;
/*
Author: BeGieU
Date: 08.02.2020
*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import m.plywacz.exchangeratesapi.exceptions.IncorrectInputException;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Notification extends BasicEntity {
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private final Set<Reading> readings = new TreeSet<>();

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false,columnDefinition = "VARCHAR(8)")
    private Currency currency;

    @Column(nullable = false)
    private int frequency;

    @Column(nullable = false)
    private double sendingFloor;  //when the value is reached, job should send notifying mail

    public Notification() {
    }


    //todo insert toString and Equals hashcode, when POJO is finished


    @JsonIgnore
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

    public void setCurrency(String currency) {
        //todo handle exception
        try {
            this.currency = Currency.valueOf(currency);
        }
        catch (IllegalArgumentException e) {
            throw new IncorrectInputException(e, "currency", currency, "couldn't find appropriate enum value");
        }
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getSendingFloor() {
        return sendingFloor;
    }

    public void setSendingFloor(double sendingFloor) {
        this.sendingFloor = sendingFloor;
    }
}
