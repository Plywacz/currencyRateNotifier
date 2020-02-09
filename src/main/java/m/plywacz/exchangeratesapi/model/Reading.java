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
    private Long currencyValue;

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

    public Long getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Long currencyValue) {
        this.currencyValue = currencyValue;
    }


}
