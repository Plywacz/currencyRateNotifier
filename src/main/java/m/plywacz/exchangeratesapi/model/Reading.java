package m.plywacz.exchangeratesapi.model;
/*
Author: BeGieU
Date: 08.02.2020
*/

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reading extends BasicEntity implements Comparable<Reading> {
    @Column(nullable = false, updatable = false)
    private LocalDate dateOfRead;

    @Column(nullable = false, updatable = false)
    private Double currencyValue;

    @Column(nullable = false, updatable = false,columnDefinition = "VARCHAR(8)")
    private Currency currency;

    @ManyToOne
    private Notification notification;

    public Reading() {
    }

    @Override
    public int compareTo(Reading other) {
        return this.dateOfRead.compareTo(other.dateOfRead);
    }
    //todo insert toString and Equals hashcode, when POJO is finished


    public LocalDate getDateOfRead() {
        return dateOfRead;
    }

    public void setDateOfRead(LocalDate date) {
        this.dateOfRead = date;
    }

    public Double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
