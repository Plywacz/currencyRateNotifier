package m.plywacz.exchangeratesapi.model;
/*
Author: BeGieU
Date: 08.02.2020
*/

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Reading extends BasicEntity implements Comparable<Reading> {
    private LocalDate date;
    private Long currencyValue;

    @ManyToOne
    private Notification notification;

    public Reading() {
    }

    @Override
    public int compareTo(Reading other) {
        return this.date.compareTo(other.date);
    }
    //todo insert toString and Equals hashcode, when POJO is finished


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Long currencyValue) {
        this.currencyValue = currencyValue;
    }


}
