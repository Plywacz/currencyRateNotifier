package m.plywacz.exchangeratesapi.model;
/*
Author: BeGieU
Date: 08.02.2020
*/

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reading extends BasicEntity implements Comparable<Reading> {
    @Column(nullable = false, updatable = false)
    private LocalDate dateOfRead;

    @Column(nullable = false, updatable = false)
    private Double currencyValue;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false, columnDefinition = "VARCHAR(8)")
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

    @JsonIgnore
    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Reading otherReading = (Reading) o;
        return Objects.equals(this.getId(), otherReading.getId());
    }

    @Override public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override public String toString() {
        return "Reading{" +
                "dateOfRead=" + dateOfRead +
                ", currencyValue=" + currencyValue +
                ", currency=" + currency +
                ", notification=" + notification +
                "} " + super.toString();
    }
}
