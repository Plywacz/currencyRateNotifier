package m.plywacz.exchangeratesapi.dto;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.constraints.Currency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NotificationDto {
    @NotNull
    @Min(value = 0, message = "User's id must be positive integer")
    private final Long userId;

    @NotNull
    @Min(value = 0, message = "Frequency of currency rate verification must be positive integer, [hours]")
    private final int frequency;

    @NotNull
    @Currency(message = "Given currency value is invalid either unsupported. Supported values: EUR, USD, GBP, CHF, JPY")
    private final String currency;

    @NotNull
    @Positive(message = "Currency value be positive number")
    private final Double currencyVal;

    public NotificationDto(Long userId, int frequency,
                           String currency, double currencyVal) {
        this.userId = userId;
        this.frequency = frequency;
        this.currency = currency;
        this.currencyVal = currencyVal;
    }

    public Long getUserId() {
        return userId;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getCurrency() {
        return currency;
    }

    public double getCurrencyVal() {
        return currencyVal;
    }
}
