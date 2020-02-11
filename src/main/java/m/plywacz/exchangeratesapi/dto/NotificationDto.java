package m.plywacz.exchangeratesapi.dto;
/*
Author: BeGieU
Date: 09.02.2020
*/

import javax.validation.constraints.NotBlank;

public class NotificationDto {
    private final Long userId;
    private final int frequency;
    private final String currency;
    private final double currencyVal;

    public NotificationDto(@NotBlank Long userId, @NotBlank int frequency,
                           @NotBlank String currency, @NotBlank double currencyVal) {
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
